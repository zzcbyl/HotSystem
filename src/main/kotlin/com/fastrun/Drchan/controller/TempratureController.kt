package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.model.Temprature
import com.fastrun.TempCollection.service.TempratureService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource


@RequestMapping("/admin/temprature/")
@Controller
class TempratureController {
    @Resource
    var _service: TempratureService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Temprature? = _service?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Temprature): ResponseData {
        val re = ResponseData()
        var result = _service?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Temprature): ResponseData {
        val re = ResponseData()
        var result = _service?.update(model)
        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list(): ModelAndView {
        var m = ModelAndView()
        m.viewName = "/admin/temprature/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchPositionID", defaultValue = "-1", required = false) positionID: Int,
                  @RequestParam(name = "searchDeviceSN", defaultValue = "", required = false) deviceSN: String,
                  @RequestParam(name = "searchCompanyID", defaultValue = "-1", required = false) companyID: Int,
                  @RequestParam(name = "searchStationID", defaultValue = "-1", required = false) stationID: Int,
                  @RequestParam(name = "searchCommunityID", defaultValue = "-1", required = false) communityID: Int,
                  @RequestParam(name = "searchBuildingID", defaultValue = "-1", required = false) buildingID: Int,
                  @RequestParam(name = "searchRoomID", defaultValue = "-1", required = false) roomID: Int,
                  @RequestParam(name = "searchCustomerName", defaultValue = "", required = false) customerName: String,
                  @RequestParam(name = "searchPhoneNumber", defaultValue = "", required = false) phoneNumber: String,
                  @RequestParam(name = "searchMinTemp", defaultValue = "0", required = false) minTemp: Int,
                  @RequestParam(name = "searchMaxTemp", defaultValue = "0", required = false) maxTemp: Int,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "desc", required = false) sortorder: String): String {

        var orderBy = StringBuilder().append(sortname).append(" ").append(sortorder).toString()
        var items = _service?.getPaging(positionID, deviceSN, companyID, stationID, communityID, buildingID, customerName, phoneNumber, minTemp, maxTemp, (offset - 1) * pageSize, pageSize, orderBy)
        var counts = _service?.getCount(positionID, deviceSN, companyID, stationID, communityID, buildingID, customerName, phoneNumber, minTemp, maxTemp)

        val jsonMap = HashMap<String, Any>()
        jsonMap["Rows"] = items!!
        jsonMap["Total"] = counts!!
        val mapper = ObjectMapper()
        var result = mapper.writeValueAsString(jsonMap)
        return result
    }

    /*
    * 只修改状态
    * */
    @PostMapping("delete")
    @ResponseBody
    fun delete(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result = _service?.delete(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("getList")
    @ResponseBody
    fun getList(@RequestParam(name = "deviceID", defaultValue = "-1", required = false) deviceID: Int,
                @RequestParam(name = "positionID", defaultValue = "-1", required = false) positionID: Int,
                @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                @RequestParam(name = "sortorder", defaultValue = "desc", required = false) sortorder: String): String {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.add(10, -24)
        var newDate = cal.getTime();

        val orderBy = "$sortname $sortorder"
        var items = _service?.getPaging(deviceID, positionID, newDate, Date(), (offset - 1) * pageSize, pageSize, orderBy)
        var counts = _service?.getCount(deviceID, positionID, newDate, Date())

        val jsonMap = HashMap<String, Any>()
        jsonMap["Rows"] = items!!
        jsonMap["Total"] = counts!!
        val mapper = ObjectMapper()
        var result = mapper.writeValueAsString(jsonMap)
        return result
    }
}


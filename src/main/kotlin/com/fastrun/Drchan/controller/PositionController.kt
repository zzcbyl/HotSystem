package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.model.Position
import com.fastrun.TempCollection.service.PositionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource


@RequestMapping("/admin/position/")
@Controller
class PositionController {
    @Resource
    var _service: PositionService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Position? = _service?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Position): ResponseData {
        val re = ResponseData()
        var position = _service?.getByName(model.communityID, model.buildingID, model.subNumber, model.floorNumber, model.apartNumber, model.roomName)
        if (position != null && position.id > 0) {
            re.putDataValue("result", 0)
            re.putDataValue("message", "安装位置已存在")
            return re
        }
        var result = _service?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Position): ResponseData {
        val re = ResponseData()
        var position = _service?.getByName(model.communityID, model.buildingID, model.subNumber, model.floorNumber, model.apartNumber, model.roomName)
        if (position != null && position.id > 0 && position.id !== model.id) {
            re.putDataValue("result", 0)
            re.putDataValue("message", "安装位置已存在")
            return re
        }
        var result = _service?.update(model)
        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list(): ModelAndView {
        var m = ModelAndView()
        m.viewName = "/admin/position/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchPositionID", defaultValue = "-1", required = true) positionID: Int,
                  @RequestParam(name = "searchCustomerName", defaultValue = "", required = true) customerName: String,
                  @RequestParam(name = "searchCompanyID", defaultValue = "-1", required = true) companyID: Int,
                  @RequestParam(name = "searchStationID", defaultValue = "-1", required = true) stationID: Int,
                  @RequestParam(name = "searchHeatExchangerUnitID", defaultValue = "-1", required = true) heatExchangerUnitID: Int,
                  @RequestParam(name = "searchCommunityID", defaultValue = "-1", required = true) communityID: Int,
                  @RequestParam(name = "searchBuildingID", defaultValue = "-1", required = true) buildingID: Int,
                  @RequestParam(name = "searchSubNumber", defaultValue = "-1", required = true) subNumber: Int,
                  @RequestParam(name = "searchFloorNumber", defaultValue = "-1", required = true) floorNumber: Int,
                  @RequestParam(name = "searchApartNumber", defaultValue = "-1", required = true) apartNumber: Int,
                  @RequestParam(name = "searchRoomName", defaultValue = "", required = true) roomName: String,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "desc", required = false) sortorder: String): String {

        var orderBy = sortname + " " + sortorder
        var items = _service?.getPaging(positionID, customerName, companyID, stationID, heatExchangerUnitID, communityID, buildingID, subNumber, floorNumber, apartNumber, roomName, (offset - 1) * pageSize, pageSize, orderBy);
        var counts = _service?.getCount(positionID, customerName, companyID, stationID, heatExchangerUnitID, communityID, buildingID, subNumber, floorNumber, apartNumber, roomName);

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

    @PostMapping("getByName")
    @ResponseBody
    fun getByName(@RequestParam(name = "communityID", defaultValue = "-1", required = true) communityID: Int,
                  @RequestParam(name = "buildingID", defaultValue = "-1", required = true) buildingID: Int,
                  @RequestParam(name = "subNumber", defaultValue = "0", required = true) subNumber: Int,
                  @RequestParam(name = "floorNumber", defaultValue = "0", required = true) floorNumber: Int,
                  @RequestParam(name = "apartNumber", defaultValue = "0", required = true) apartNumber: Int,
                  @RequestParam(name = "roomName", defaultValue = "", required = true) roomName: String,
                  @RequestParam(name = "id", defaultValue = "0", required = false) id: Int): ResponseData {
        val re = ResponseData()
        var position = _service?.getByName(communityID, buildingID, subNumber, floorNumber, apartNumber, roomName)
        if (position != null && position.id > 0)
            if (id === 0) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "安装位置已存在")
            } else if (position.id !== id) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "安装位置已存在")
            }
        return re
    }
}


package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.model.Device
import com.fastrun.TempCollection.model.Deviceinstall
import com.fastrun.TempCollection.service.DeviceService
import com.fastrun.TempCollection.service.DeviceinstallService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource


@RequestMapping("/admin/deviceinstall/")
@Controller
class DeviceinstallController {
    @Resource
    var _service: DeviceinstallService? = null
    @Resource
    val _deviceService: DeviceService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Deviceinstall? = _service?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Deviceinstall): ResponseData {
        val re = ResponseData()
        val device = _deviceService?.getBySN(model.deviceSN)
        if (device != null) {
            model.deviceID = device.id
        } else {
            val deviceModel = Device()
            deviceModel.deviceSN = model.deviceSN
            deviceModel.equipmentChipID = model.equipmentChipID
            deviceModel.communicationCard = model.communicationCard
            deviceModel.equipmentPhone = model.equipmentPhone
            deviceModel.remark = model.remark
            deviceModel.status = model.status
            deviceModel.createrID = model.createrID
            var deviceResult = _deviceService?.insert(deviceModel);
            if (deviceResult != null)
                model.deviceID = deviceModel.id
        }

        var result = _service?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Deviceinstall): ResponseData {
        val re = ResponseData()
        val device = _deviceService?.getBySN(model.deviceSN);
        if (device == null) {
            val deviceModel = Device()
            deviceModel.deviceSN = model.deviceSN
            deviceModel.equipmentChipID = model.equipmentChipID
            deviceModel.communicationCard = model.communicationCard
            deviceModel.equipmentPhone = model.equipmentPhone
            deviceModel.remark = model.remark
            deviceModel.status = model.status
            deviceModel.createrID = model.createrID
            var deviceResult = _deviceService?.insert(deviceModel);
            if (deviceResult != null)
                model.deviceID = deviceModel.id
        } else {
            model.deviceID = device.id
            device.deviceSN = model.deviceSN
            device.equipmentChipID = model.equipmentChipID
            device.communicationCard = model.communicationCard
            device.equipmentPhone = model.equipmentPhone
            device.remark = model.remark
            device.status = model.status
            device.updateTime = Date()
            _deviceService?.update(device)
        }

        var result = _service?.update(model)
        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list(): ModelAndView {
        var m = ModelAndView()
        m.viewName = "/admin/deviceinstall/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchDeviceSN", defaultValue = "", required = false) deviceSN: String,
                  @RequestParam(name = "searchCustomerName", defaultValue = "", required = false) customerName: String,
                  @RequestParam(name = "searchPhoneNumber", defaultValue = "", required = false) phoneNumber: String,
                  @RequestParam(name = "searchCompanyID", defaultValue = "-1", required = false) companyID: Int,
                  @RequestParam(name = "searchStationID", defaultValue = "-1", required = false) stationID: Int,
                  @RequestParam(name = "searchCommunityID", defaultValue = "-1", required = false) communityID: Int,
                  @RequestParam(name = "searchBuildingID", defaultValue = "-1", required = false) buildingID: Int,
                  @RequestParam(name = "searchRoomID", defaultValue = "-1", required = false) roomID: Int,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "desc", required = false) sortorder: String): String {

        val orderBy = "$sortname $sortorder"
        var items = _service?.getPaging(deviceSN, customerName, phoneNumber, companyID, stationID, communityID, buildingID, roomID, (offset - 1) * pageSize, pageSize, orderBy)
        var counts = _service?.getCount(deviceSN, customerName, phoneNumber, companyID, stationID, communityID, buildingID, roomID)

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


}


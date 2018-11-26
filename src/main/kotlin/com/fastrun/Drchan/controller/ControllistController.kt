package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.model.Controllist
import com.fastrun.TempCollection.model.Controlrecord
import com.fastrun.TempCollection.model.Deviceinstall
import com.fastrun.TempCollection.service.ControllistService
import com.fastrun.TempCollection.service.ControlrecordService
import com.fastrun.TempCollection.service.DeviceService
import com.fastrun.TempCollection.service.DeviceinstallService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource


@RequestMapping("/admin/controllist/")
@Controller
class ControllistController {
    @Resource
    var _service: ControllistService? = null
    @Resource
    val _recordService: ControlrecordService? = null
    @Resource
    val _deviceService: DeviceService? = null
    @Resource
    val _deviceInstallService: DeviceinstallService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Controllist? = _service?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Controllist): ResponseData {
        val re = ResponseData()
        if (model.remoteCode.equals("Parameter1"))
            model.executeStatus = 2;
        var result = _service?.insert(model)
        if (result != null) {
            if (model.rangeType.equals(1)) { //设备
                var device = _deviceService?.get(model.rangeObjectID.toInt())
                if (device != null) {
                    var record = Controlrecord()
                    record.controlID = model.id
                    record.deviceID = device.id
                    record.deviceSN = device.deviceSN
                    record.executeStatus = model.executeStatus
                    record.note = model.note
                    record.remoteCode = model.remoteCode
                    _recordService?.insert(record)
                }
            } else {
                var installList: List<Deviceinstall>? = null
                if (model.rangeType.equals(4)) { //所有设备
                    installList = _deviceInstallService?.getPaging("", "", "", -1, -1, -1, -1, -1, 0, 99999, "id desc")
                } else if (model.rangeType.equals(2)) { //小区
                    installList = _deviceInstallService?.getPaging("", "", "", -1, -1, model.rangeObjectID.toInt(), -1, -1, 0, 99999, "id desc")
                } else if (model.rangeType.equals(3)) { //换热站
                    installList = _deviceInstallService?.getPaging("", "", "", -1, model.rangeObjectID.toInt(), -1, -1, -1, 0, 99999, "id desc")
                }
                var iterator = installList?.iterator();
                while (iterator != null && iterator.hasNext()) {
                    var deviceInstall = iterator.next()
                    var record = Controlrecord()
                    record.controlID = model.id
                    record.deviceID = deviceInstall.deviceID
                    record.deviceSN = deviceInstall.deviceSN
                    record.executeStatus = model.executeStatus
                    record.note = model.note
                    record.remoteCode = model.remoteCode
                    _recordService?.insert(record)
                }
            }
        }
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Controllist): ResponseData {
        val re = ResponseData()
        var result = _service?.update(model)
        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list(): ModelAndView {
        var m = ModelAndView()
        m.viewName = "/admin/controllist/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchRemoteCode", defaultValue = "", required = true) remoteCode: String,
                  @RequestParam(name = "searchExecuteStatus", defaultValue = "-1", required = true) executeStatus: Int,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "desc", required = false) sortorder: String): String {
        val orderBy = "$sortname $sortorder"
        var items = _service?.getPaging(remoteCode, executeStatus, (offset - 1) * pageSize, pageSize, orderBy)
        var counts = _service?.getCount(remoteCode, executeStatus)

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
        _recordService?.deleteByControlID(id);
        re.putDataValue("result", result)
        return re;
    }


}


package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.model.Community
import com.fastrun.TempCollection.service.CommunityService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource


@RequestMapping("/admin/community/")
@Controller
class CommunityController {
    @Resource
    var _service: CommunityService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Community? = _service?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Community): ResponseData {
        val re = ResponseData()
        val community = _service?.getByName(model.name, model.stationID)
        if (community != null && community.id > 0) {
            re.putDataValue("result", 0)
            re.putDataValue("message", "小区名称已存在")
            return re
        }
        var result = _service?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Community): ResponseData {
        val re = ResponseData()
        val community = _service?.getByName(model.name, model.stationID)
        if (community != null && community.id > 0 && community.id != model.id) {
            re.putDataValue("result", 0)
            re.putDataValue("message", "小区名称已存在")
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
        m.viewName = "/admin/community/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchName", defaultValue = "", required = false) name: String,
                  @RequestParam(name = "searchCompanyID", defaultValue = "-1", required = false) companyID: Int,
                  @RequestParam(name = "searchStationID", defaultValue = "-1", required = false) stationID: Int,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "desc", required = false) sortorder: String): String {
        var orderBy = sortname + " " + sortorder;
        var items = _service?.getPaging(name, companyID, stationID, (offset - 1) * pageSize, pageSize, orderBy)
        var counts = _service?.getCount(name, companyID, stationID)

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
    fun getByName(@RequestParam(name = "name", required = true) name: String,
                  @RequestParam(name = "stationID", defaultValue = "0", required = false) stationID: Int,
                  @RequestParam(name = "id", defaultValue = "0", required = false) id: Int): ResponseData {
        val re = ResponseData()
        var community = _service?.getByName(name, stationID)
        if (community != null && community.id > 0)
            if (id === 0) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "小区名称已存在")
            } else if (community.id != id) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "小区名称已存在")
            }
        return re
    }

}


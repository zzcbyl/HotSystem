package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import javax.annotation.Resource
import com.fastrun.TempCollection.model.Company
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import com.fastrun.TempCollection.service.CompanyService
import com.fastrun.TempCollection.ResponseData
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.PostMapping


@RequestMapping("/admin/company/")
@Controller
class CompanyController {
    @Resource
    var _service: CompanyService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Company? = _service?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Company): ResponseData {
        val re = ResponseData()
        var result = _service?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Company): ResponseData {
        val re = ResponseData()
        var result = _service?.update(model)
        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list(): ModelAndView {
        var m = ModelAndView()
        m.viewName = "/admin/company/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchCompanyName", defaultValue = "", required = false) name: String,
                  @RequestParam(name = "searchParentID", defaultValue = "-1", required = false) parentID: Int,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "asc", required = false) sortorder: String): String {

        var orderBy = sortname + " " + sortorder;
        var items = _service?.getPaging(name, parentID, offset - 1, pageSize, orderBy)
        var counts = _service?.getCount(name, parentID)

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
    fun getByName(@RequestParam(name = "name", required = true) name: String, @RequestParam(name = "id", defaultValue = "0", required = false) id: Int): ResponseData {
        val re = ResponseData()
        var company = _service?.getByName(name)
        if (company != null && company.id > 0) {
            if (id == 0) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "公司名称已存在")
            } else if (company.id != id) {
                re.putDataValue("result", 0);
                re.putDataValue("message", "公司名称已存在");
            }
        }

        return re;
    }
}


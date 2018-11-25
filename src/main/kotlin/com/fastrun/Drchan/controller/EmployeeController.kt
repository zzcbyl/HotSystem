package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.auth.Audience
import com.fastrun.TempCollection.auth.JwtHelper
import com.fastrun.TempCollection.common.AESEncoder
import com.fastrun.TempCollection.common.Encode
import com.fastrun.TempCollection.model.Employee
import com.fastrun.TempCollection.service.EmployeeService
import org.springframework.web.bind.annotation.*
import java.util.HashMap

@RequestMapping("/admin/employee/")
@Controller
class EmployeeController {
    @Resource
    var employService: EmployeeService? = null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id: Int): ResponseData {
        val re = ResponseData()
        var result: Employee? = employService?.get(id)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Employee): ResponseData {
        val re = ResponseData()
        model.password = AESEncoder.AESEncode(AESEncoder.salt, model.password).toString();
        var result = employService?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Employee): ResponseData {
        val re = ResponseData()
        var result = employService?.update(model)
        re.putDataValue("result", result)
        return re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list(): ModelAndView {
        var m = ModelAndView()
        m.viewName = "/admin/employee/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name = "searchAccount", defaultValue = "", required = true) account: String,
                  @RequestParam(name = "searchCompanyID", defaultValue = "-1", required = true) companyID: Int,
                  @RequestParam(name = "searchStationID", defaultValue = "-1", required = true) stationID: Int,
                  @RequestParam(name = "page", defaultValue = "1", required = true) offset: Int,
                  @RequestParam(name = "pagesize", defaultValue = "20", required = true) pageSize: Int,
                  @RequestParam(name = "sortname", defaultValue = "id", required = false) sortname: String,
                  @RequestParam(name = "sortorder", defaultValue = "asc", required = false) sortorder: String): String {
        var orderBy = sortname + " " + sortorder;
        var items = employService?.getPaging(account, companyID, stationID, offset - 1, pageSize, orderBy)
        var counts = employService?.getCount(account, companyID, stationID)

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
        var result = employService?.delete(id)
        re.putDataValue("result", result)
        return re;
    }

    /*
    *重置密码
     */
    @PostMapping("resetPassword")
    @ResponseBody
            //fun resetPassword(@RequestParam(required = true) id: Int, @RequestParam(required = true) password: String): ResponseData {
    fun resetPassword(@RequestBody model: Employee): ResponseData {
        val re = ResponseData()
        var newPassword = AESEncoder.AESEncode(AESEncoder.salt, model.password).toString();
        var result = employService?.resetPassword(model.id, newPassword)
        re.putDataValue("result", result)
        return re;
    }
}


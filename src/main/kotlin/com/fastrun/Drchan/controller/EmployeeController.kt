package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastrun.Drchan.common.MD5Encrpyt
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.common.AESEncoder
import com.fastrun.TempCollection.model.Employee
import com.fastrun.TempCollection.service.EmployeeService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource


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
        val employee = employService?.getByName(model.account)
        if (employee != null && employee.id > 0) {
            re.putDataValue("result", 0)
            re.putDataValue("message", "账号已存在")
            return re
        }
        model.password = AESEncoder.AESEncode(AESEncoder.salt, model.password).toString();
        var result = employService?.insert(model)
        re.putDataValue("result", result)
        return re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Employee): ResponseData {
        val re = ResponseData()
        val employee = employService?.getByName(model.account)
        if (employee != null && employee.id > 0 && employee.id != model.id) {
            re.putDataValue("result", 0)
            re.putDataValue("message", "账号已存在")
            return re
        }
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
        var items = employService?.getPaging(account, companyID, stationID, (offset - 1) * pageSize, pageSize, orderBy)
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

    @GetMapping("changePassword")
    @ResponseBody
    fun changePassword(): ModelAndView {
        return ModelAndView("/admin/employee/changePassword")
    }

    @PostMapping("changePassword")
    @ResponseBody
    fun changePasswordPost(@RequestParam id: Int, @RequestParam orgPassword: String, @RequestParam newPassword: String): ResponseData {
        val res = ResponseData()
        val user = employService?.get(id)
        val orgPwd = MD5Encrpyt.MD5Encode(orgPassword)
        if (user == null || orgPwd != user.password) {
            res.putDataValue("Result", "error")
            res.putDataValue("Message", "原密码错误")
            return res;
        }
        var pwdNew = MD5Encrpyt.MD5Encode(newPassword).toString()
        var result = employService?.changPassword(id, pwdNew)
        res.putDataValue("Result", "OK");
        res.putDataValue("Message", result);
        return res;
    }

    @PostMapping("getByName")
    @ResponseBody
    fun getByName(@RequestParam(name = "name", required = true) account: String,
                  @RequestParam(name = "id", defaultValue = "0", required = false) id: Int): ResponseData {
        val re = ResponseData()
        val employee = employService?.getByName(account)
        if (employee != null && employee.id > 0)
            if (id === 0) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "账号已存在")
            } else if (employee.id != id) {
                re.putDataValue("result", 0)
                re.putDataValue("message", "账号已存在")
            }
        return re
    }
}


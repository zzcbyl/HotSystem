package com.fastrun.TempCollection.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import java.util.*
import javax.annotation.Resource
import com.fastrun.TempCollection.ResponseData
import com.fastrun.TempCollection.model.Devicemonitor
import com.fastrun.TempCollection.service.DevicemonitorService
import org.springframework.web.bind.annotation.*
import java.util.HashMap

@RequestMapping("/admin/devicemonitor/")
@Controller
class DevicemonitorController {
    @Resource
    var _service: DevicemonitorService?=null

    @GetMapping("get")
    @ResponseBody
    fun get(@RequestParam id:Int):ResponseData
    {
        val re =ResponseData()
        var result:Devicemonitor?=_service?.get(id)
        re.putDataValue("result",result)
        return  re;
    }
    
    @PostMapping("create")
    @ResponseBody
    fun create(@RequestBody model: Devicemonitor):ResponseData
    {
        val re =ResponseData()
        var result=_service?.insert(model)
        re.putDataValue("result",result)
        return  re;
    }

    @PostMapping("update")
    @ResponseBody
    fun update(@RequestBody model: Devicemonitor):ResponseData
    {
        val re =ResponseData()
        var result=_service?.update(model)
        re.putDataValue("result",result)
        return  re;
    }

    @GetMapping("list")
    @ResponseBody
    fun list():ModelAndView
    {
        var m=ModelAndView()
        m.viewName="/admin/devicemonitor/list"
        return m;
    }

    @PostMapping("getPaging")
    @ResponseBody
    fun getPaging(@RequestParam(name="jtStartIndex", defaultValue="0",required = true) offset:Int,@RequestParam(name="jtPageSize") pageSize:Int,@RequestParam(name="jtSorting",defaultValue = "id desc",required = false) orderBy:String):String
    {
        var items= _service?.getPaging(offset,pageSize,orderBy)
        var counts=_service?.getCount()

        val jsonMap = HashMap<String, Any>()
        jsonMap["Result"] = "OK"
        jsonMap["Records"] = items!!
        jsonMap["TotalRecordCount"]=counts!!
        val mapper = ObjectMapper()
        var result= mapper.writeValueAsString(jsonMap)
        return result
    }

    /*
    * 只修改状态
    * */
    @PostMapping("delete")
    @ResponseBody
    fun delete(@RequestParam id: Int):ResponseData
    {
        val re =ResponseData()
        var result=_service?.delete(id)
        re.putDataValue("result",result)
        return  re;
    }


}


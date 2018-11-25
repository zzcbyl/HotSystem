package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Deviceinstallrecord
import org.apache.ibatis.annotations.*

@Mapper
interface DeviceinstallrecordMapper {

    fun insert(@Param("model") model: Deviceinstallrecord): Int

    fun update(@Param("model") model:Deviceinstallrecord):Int
    
    fun get(@Param("id") id:Int):Deviceinstallrecord

    fun getPaging(@Param("offset") offset:Int,@Param("pageSize") pageSize:Int,@Param("orderBy") orderBy:String): List<Deviceinstallrecord>

    fun getCount():Int

    fun delete(@Param("id") id:Int): Int

}

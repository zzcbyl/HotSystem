package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Devicemonitor
import org.apache.ibatis.annotations.*

@Mapper
interface DevicemonitorMapper {

    fun insert(@Param("model") model: Devicemonitor): Int

    fun update(@Param("model") model: Devicemonitor): Int

    fun get(@Param("id") id: Int): Devicemonitor

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Devicemonitor>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

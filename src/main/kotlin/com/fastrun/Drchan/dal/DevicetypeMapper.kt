package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Devicetype
import org.apache.ibatis.annotations.*

@Mapper
interface DevicetypeMapper {

    fun insert(@Param("model") model: Devicetype): Int

    fun update(@Param("model") model: Devicetype): Int

    fun get(@Param("id") id: Int): Devicetype

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Devicetype>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

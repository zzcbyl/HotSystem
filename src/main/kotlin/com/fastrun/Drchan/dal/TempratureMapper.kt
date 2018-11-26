package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Temprature
import org.apache.ibatis.annotations.*

@Mapper
interface TempratureMapper {

    fun insert(@Param("model") model: Temprature): Int

    fun update(@Param("model") model: Temprature): Int

    fun get(@Param("id") id: Int): Temprature

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Temprature>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

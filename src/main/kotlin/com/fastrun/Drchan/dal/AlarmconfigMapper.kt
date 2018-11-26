package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Alarmconfig
import org.apache.ibatis.annotations.*

@Mapper
interface AlarmconfigMapper {

    fun insert(@Param("model") model: Alarmconfig): Int

    fun update(@Param("model") model: Alarmconfig): Int

    fun get(@Param("id") id: Int): Alarmconfig

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Alarmconfig>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

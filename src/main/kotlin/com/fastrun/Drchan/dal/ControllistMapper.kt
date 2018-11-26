package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Controllist
import org.apache.ibatis.annotations.*

@Mapper
interface ControllistMapper {

    fun insert(@Param("model") model: Controllist): Int

    fun update(@Param("model") model: Controllist): Int

    fun get(@Param("id") id: Int): Controllist

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Controllist>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

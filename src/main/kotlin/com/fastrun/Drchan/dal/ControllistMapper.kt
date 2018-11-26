package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Controllist
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface ControllistMapper {

    fun insert(@Param("model") model: Controllist): Int

    fun update(@Param("model") model: Controllist): Int

    fun get(@Param("id") id: Int): Controllist

    fun getPaging(@Param("remoteCode") remoteCode: String, @Param("executeStatus") executeStatus: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Controllist>

    fun getCount(@Param("remoteCode") remoteCode: String, @Param("executeStatus") executeStatus: Int): Int

    fun delete(@Param("id") id: Int): Int

}

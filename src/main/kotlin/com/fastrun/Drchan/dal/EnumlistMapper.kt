package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Enumlist
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface EnumlistMapper {

    fun insert(@Param("model") model: Enumlist): Int

    fun update(@Param("model") model: Enumlist): Int

    fun get(@Param("id") id: Int): Enumlist

    fun getPaging(@Param("groupNo") groupNo: String, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Enumlist>

    fun getCount(@Param("groupNo") groupNo: String): Int

    fun delete(@Param("id") id: Int): Int

}

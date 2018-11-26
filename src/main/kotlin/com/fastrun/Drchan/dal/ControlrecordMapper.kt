package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Controlrecord
import org.apache.ibatis.annotations.*

@Mapper
interface ControlrecordMapper {

    fun insert(@Param("model") model: Controlrecord): Int

    fun update(@Param("model") model: Controlrecord): Int

    fun get(@Param("id") id: Int): Controlrecord

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Controlrecord>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Controlrecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface ControlrecordMapper {

    fun insert(@Param("model") model: Controlrecord): Int

    fun update(@Param("model") model: Controlrecord): Int

    fun get(@Param("id") id: Int): Controlrecord

    fun getPaging(@Param("controlID") controlID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Controlrecord>

    fun getCount(@Param("controlID") controlID: Int): Int

    fun delete(@Param("id") id: Int): Int

    fun deleteByControlID(@Param("controlID") controlID: Int): Int

}

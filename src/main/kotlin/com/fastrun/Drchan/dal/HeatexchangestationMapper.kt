package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Heatexchangestation
import org.apache.ibatis.annotations.*

@Mapper
interface HeatexchangestationMapper {

    fun insert(@Param("model") model: Heatexchangestation): Int

    fun update(@Param("model") model: Heatexchangestation): Int

    fun get(@Param("id") id: Int): Heatexchangestation

    fun getPaging(@Param("name") name: String, @Param("companyID") companyID: Int, @Param("parentID") parentID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Heatexchangestation>

    fun getCount(@Param("name") name: String, @Param("companyID") companyID: Int, @Param("parentID") parentID: Int): Int

    fun delete(@Param("id") id: Int): Int

    fun getByName(@Param("name") paramString: String, @Param("companyID") paramInt: Int): Heatexchangestation

}

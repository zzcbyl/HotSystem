package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Company
import org.apache.ibatis.annotations.*


@Mapper
interface CompanyMapper {

    fun insert(@Param("model") model: Company): Int

    fun update(@Param("model") model: Company): Int

    fun get(@Param("id") id: Int): Company

    fun getPaging(@Param("name") name: String, @Param("parentID") parentID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Company>

    fun getCount(@Param("name") name: String, @Param("parentID") parentID: Int): Int

    fun delete(@Param("id") id: Int): Int

    fun getByName(@Param("name") paramString: String): Company
}

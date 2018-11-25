package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Community
import org.apache.ibatis.annotations.*

@Mapper
interface CommunityMapper {

    fun insert(@Param("model") model: Community): Int

    fun update(@Param("model") model: Community): Int

    fun get(@Param("id") id: Int): Community

    fun getPaging(@Param("name") name: String, @Param("stationID") stationID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Community>

    fun getCount(@Param("name") name: String, @Param("stationID") stationID: Int): Int

    fun delete(@Param("id") id: Int): Int

}

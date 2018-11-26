package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Building
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param


@Mapper
interface BuildingMapper {

    fun insert(@Param("model") model: Building): Int

    fun update(@Param("model") model: Building): Int

    fun get(@Param("id") id: Int): Building

    fun getPaging(@Param("name") name: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Building>

    fun getCount(@Param("name") name: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int): Int

    fun delete(@Param("id") id: Int): Int

    fun getByName(@Param("name") name: String, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int): Building

}

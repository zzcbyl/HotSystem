package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Temprature
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.util.*


@Mapper
interface TempratureMapper {

    fun insert(@Param("model") model: Temprature): Int

    fun update(@Param("model") model: Temprature): Int

    fun get(@Param("id") id: Int): Temprature

    fun getPaging(@Param("positionID") positionID: Int, @Param("deviceSN") deviceSN: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int,  @Param("customerName") customerName: String,  @Param("phoneNumber") phoneNumber: String, @Param("minTemp") minTemp: Int, @Param("maxTemp") maxTemp: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int,  @Param("orderBy") orderBy: String): List<Temprature>

    fun getPaging1(@Param("deviceID") deviceID: Int, @Param("positionID") positionID: Int, @Param("beginDate") beginDate: Date, @Param("endDate") endDate: Date, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Temprature>

    fun getCount(@Param("positionID") positionID: Int,  @Param("deviceSN") deviceSN: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int,  @Param("customerName") customerName: String,  @Param("phoneNumber") phoneNumber: String, @Param("minTemp") minTemp: Int, @Param("maxTemp") maxTemp: Int): Int

    fun getCount1(@Param("deviceID") deviceID: Int, @Param("positionID") positionID: Int,  @Param("beginDate") beginDate: Date,  @Param("endDate") endDate: Date): Int

    fun delete(@Param("id") id: Int): Int

}

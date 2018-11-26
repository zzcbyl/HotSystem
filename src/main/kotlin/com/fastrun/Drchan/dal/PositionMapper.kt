package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Position
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface PositionMapper {

    fun insert(@Param("model") model: Position): Int

    fun update(@Param("model") model: Position): Int

    fun get(@Param("id") id: Int): Position

    fun getPaging(@Param("positionID") positionID: Int, @Param("customerName") customerName: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("heatExchangerUnitID") heatExchangerUnitID: Int, @Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int, @Param("subNumber") subNumber: Int, @Param("floorNumber") floorNumber: Int, @Param("apartNumber") apartNumber: Int, @Param("roomName") roomName: String, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Position>

    fun getCount(@Param("positionID") positionID: Int, @Param("customerName") customerName: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("heatExchangerUnitID") heatExchangerUnitID: Int, @Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int, @Param("subNumber") subNumber: Int, @Param("floorNumber") floorNumber: Int, @Param("apartNumber") apartNumber: Int, @Param("roomName") roomName: String): Int

    fun delete(@Param("id") id: Int): Int

    fun getByName(@Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int, @Param("subNumber") subNumber: Int, @Param("floorNumber") floorNumber: Int, @Param("apartNumber") apartNumber: Int, @Param("roomName") roomName: String): Position
}

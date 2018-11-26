package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Deviceinstall
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param



@Mapper
interface DeviceinstallMapper {

    fun insert(@Param("model") model: Deviceinstall): Int

    fun update(@Param("model") model: Deviceinstall): Int

    fun get(@Param("id") id: Int): Deviceinstall

    fun getPaging(@Param("deviceSN") deviceSN: String, @Param("customerName") customerName: String,  @Param("phoneNumber") phoneNumber: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int, @Param("roomID") roomID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int,  @Param("orderBy") orderBy: String): List<Deviceinstall>

    fun getCount(@Param("deviceSN") deviceSN: String, @Param("customerName") customerName: String,  @Param("phoneNumber") phoneNumber: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("communityID") communityID: Int, @Param("buildingID") buildingID: Int, @Param("roomID") roomID: Int): Int

    fun delete(@Param("id") id: Int): Int

}

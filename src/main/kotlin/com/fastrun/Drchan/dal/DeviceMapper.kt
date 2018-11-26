package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Device
import org.apache.ibatis.annotations.*

@Mapper
interface DeviceMapper {

    fun insert(@Param("model") model: Device): Int

    fun update(@Param("model") model: Device): Int

    fun get(@Param("id") id: Int): Device

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Device>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

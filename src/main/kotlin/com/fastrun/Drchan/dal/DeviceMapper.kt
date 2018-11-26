package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Device
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param


@Mapper
interface DeviceMapper {

    fun insert(@Param("model") model: Device): Int

    fun update(@Param("model") model: Device): Int

    fun get(@Param("id") id: Int): Device

    fun getPaging(@Param("deviceSN") deviceSN: String, @Param("level1") level1: Float, @Param("level2") level2: Float, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Device>

    fun getCount(@Param("deviceSN") deviceSN: String, @Param("level1") level1: Float, @Param("level2") level2: Float): Int

    fun delete(@Param("id") id: Int): Int

    fun getBySN(@Param("deviceSN") deviceSN: String): Device

}

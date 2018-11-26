package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Room
import org.apache.ibatis.annotations.*

@Mapper
interface RoomMapper {

    fun insert(@Param("model") model: Room): Int

    fun update(@Param("model") model: Room): Int

    fun get(@Param("id") id: Int): Room

    fun getPaging(@Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Room>

    fun getCount(): Int

    fun delete(@Param("id") id: Int): Int

}

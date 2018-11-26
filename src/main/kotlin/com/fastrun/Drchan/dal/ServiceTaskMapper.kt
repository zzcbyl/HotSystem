package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.ServiceTask
import org.apache.ibatis.annotations.*

@Mapper
interface ServiceTaskMapper {

    fun insert(@Param("model") model: ServiceTask): Int

    fun update(@Param("model") model: ServiceTask): Int

    fun get(@Param("id") id: Int): ServiceTask

    fun getPaging(@Param("userId") userId: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<ServiceTask>

    fun getCount(@Param("userId") userId: Int): Int

    fun delete(@Param("id") id: Int): Int

}

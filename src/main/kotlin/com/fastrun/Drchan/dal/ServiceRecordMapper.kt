package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.ServiceRecord
import org.apache.ibatis.annotations.*

@Mapper
interface ServiceRecordMapper {

    fun insert(@Param("model") model: ServiceRecord): Int

    fun update(@Param("model") model:ServiceRecord):Int
    
    fun get(@Param("id") id:Int):ServiceRecord

    fun getPaging(@Param("offset") offset:Int,@Param("pageSize") pageSize:Int,@Param("orderBy") orderBy:String): List<ServiceRecord>

    fun getCount():Int

    fun delete(@Param("id") id:Int): Int

}

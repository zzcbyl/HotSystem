package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Customer
import org.apache.ibatis.annotations.*

@Mapper
interface CustomerMapper {

    fun insert(@Param("model") model: Customer): Int

    fun update(@Param("model") model:Customer):Int
    
    fun get(@Param("id") id:Int):Customer

    fun getPaging(@Param("offset") offset:Int,@Param("pageSize") pageSize:Int,@Param("orderBy") orderBy:String): List<Customer>

    fun getCount():Int

    fun delete(@Param("id") id:Int): Int

}

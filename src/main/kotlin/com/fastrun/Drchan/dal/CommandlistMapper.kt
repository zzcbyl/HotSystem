package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Commandlist
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface CommandlistMapper {

    fun insert(@Param("model") model: Commandlist): Int

    fun update(@Param("model") model:Commandlist):Int
    
    fun get(@Param("id") id:Int):Commandlist

    fun getPaging(@Param("offset") offset:Int,@Param("pageSize") pageSize:Int,@Param("orderBy") orderBy:String): List<Commandlist>

    fun getCount():Int

    fun delete(@Param("id") id:Int): Int

}

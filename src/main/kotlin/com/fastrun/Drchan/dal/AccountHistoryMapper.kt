package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.AccountHistory
import com.fastrun.TempCollection.model.User
import org.apache.ibatis.annotations.*

@Mapper
interface AccountHistoryMapper {

    fun insert(@Param("model") model: AccountHistory): Int

    fun update(@Param("model") model: AccountHistory):Int

    fun delete(@Param("id") id:Int): Int

    fun get(@Param("id") id:Int): AccountHistory

    /*
    * 获取记录数
    * */
    fun getCount(@Param("userId") userId:Long,@Param("type") type:Int):Int

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(@Param("userId") userId:Long,@Param("type") type:Int,@Param("offset") offset:Int,@Param("pageSize") pageSize:Int,@Param("orderBy") orderBy:String): List<AccountHistory>


}
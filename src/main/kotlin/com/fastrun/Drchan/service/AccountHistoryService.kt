package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.AccountHistory

interface AccountHistoryService {

    fun insert(model: AccountHistory): Int?

    fun update(model: AccountHistory): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): AccountHistory?

    /*
    * 获取记录数
    * */
    fun getCount(userId: Long, type: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(userId: Long, type: Int, offset: Int, pageSize: Int, orderBy: String): List<AccountHistory>?

}
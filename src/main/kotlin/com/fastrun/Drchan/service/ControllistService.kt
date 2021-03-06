package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Controllist

interface ControllistService {

    fun insert(model: Controllist): Int?

    fun update(model: Controllist): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Controllist?

    /*
    * 获取记录数
    * */
    fun getCount(remoteCode: String, executeStatus: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(remoteCode: String, executeStatus: Int, offset: Int, pageSize: Int, orderBy: String): List<Controllist>?

}

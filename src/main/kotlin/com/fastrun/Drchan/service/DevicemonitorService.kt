package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Devicemonitor

interface DevicemonitorService {

    fun insert(model: Devicemonitor): Int?

    fun update(model: Devicemonitor): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Devicemonitor?

    /*
    * 获取记录数
    * */
    fun getCount(): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(offset: Int, pageSize: Int, orderBy: String): List<Devicemonitor>?

}

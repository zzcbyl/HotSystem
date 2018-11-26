package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Device


interface DeviceService {

    fun insert(model: Device): Int?

    fun update(model: Device): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Device?

    /*
    * 获取记录数
    * */
    fun getCount(deviceSN: String,level1: Float, level2: Float): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(deviceSN: String, level1: Float, level2: Float,offset: Int, pageSize: Int, orderBy: String): List<Device>?

    fun getBySN(deviceSN: String): Device?
}

package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Controlrecord

interface ControlrecordService {

    fun insert(model: Controlrecord): Int?

    fun update(model: Controlrecord): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Controlrecord?

    /*
    * 获取记录数
    * */
    fun getCount(controlID: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(controlID: Int, offset: Int, pageSize: Int, orderBy: String): List<Controlrecord>?

    fun deleteByControlID(controlID: Int): Int?
}

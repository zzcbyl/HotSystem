package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Community


interface CommunityService {

    fun insert(model: Community): Int?

    fun update(model: Community): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Community?

    /*
    * 获取记录数
    * */
    fun getCount(name: String, companyID: Int, stationID: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(name: String, companyID: Int, stationID: Int, offset: Int, pageSize: Int, orderBy: String): List<Community>?


    fun getByName(name: String, stationID: Int): Community?

}

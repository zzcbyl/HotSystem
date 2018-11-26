package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Deviceinstall

interface DeviceinstallService {

    fun insert(model: Deviceinstall): Int?

    fun update(model: Deviceinstall): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Deviceinstall?

    /*
    * 获取记录数
    * */
    fun getCount(deviceSN: String, customerName: String, phoneNumber: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int, roomID: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(deviceSN: String, customerName: String, phoneNumber: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int, roomID: Int, offset: Int, pageSize: Int, orderBy: String): List<Deviceinstall>?

}

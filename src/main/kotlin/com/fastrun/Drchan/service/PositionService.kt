package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Position

interface PositionService {

    fun insert(model: Position): Int?

    fun update(model: Position): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Position?

    /*
    * 获取记录数
    * */
    fun getCount(positionID: Int, customerName: String, companyID: Int, stationID: Int, heatExchangerUnitID: Int, communityID: Int, buildingID: Int, subNumber: Int, floorNumber: Int, apartNumber: Int, roomName: String): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(positionID: Int, customerName: String, companyID: Int, stationID: Int, heatExchangerUnitID: Int, communityID: Int, buildingID: Int, subNumber: Int, floorNumber: Int, apartNumber: Int, roomName: String, offset: Int, pageSize: Int, orderBy: String): List<Position>?

    fun getByName(communityID: Int, buildingID: Int, subNumber: Int, floorNumber: Int, apartNumber: Int, roomName: String): Position?
}

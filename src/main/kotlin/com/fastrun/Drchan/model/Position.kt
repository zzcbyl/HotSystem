package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Position(var id: Int = -1, var customerID: Int = -1, var lati: Double = 0.0, var longi: Double = 0.0, var height: Float = 0f, var communityID: Int = -1, var buildingID: Int = -1, var subNumber: Int = -1, var floorNumber: Int = -1, var apartNumber: Int = -1, var roomName: String = "", var exactPosition: String = "", var sideRoom: Int = -1, var roomArea: Float = 0f, var status: Int = -1, var createrID: Int = -1, var createTime: Date = Date(), var heatExchangerUnitID: Int = -1, var distance: String = "", var houseHoldID: String = "", var heatingEnteryID: String = "", var heatingType: Int = -1, var remark: String = "", var updateTime: Date? = null, var customerName: String = "", var communityName: String = "", var buildingName: String = "", var heatExchangerUnitNumber: String = "", var companyID: Int = 0, var stationID: Int = 0, var deviceID: Int = 0, var deviceSN: String = "", var temp1: Float = 0f, var temp2: Float = 0f, var level: Float = 0f, var collectTime: Date? = null)

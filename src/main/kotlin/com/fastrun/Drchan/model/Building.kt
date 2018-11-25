package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Building(var id: Int = -1, var name: String = "", var buildingNo: String = "", var communityID: Int = -1, var stationID: Int = -1, var lati1: Double = 0.0, var longi1: Double = 0.0, var lati2: Double = 0.0, var longi2: Double = 0.0, var height: Double = 0.0, var status: Int = -1, var createrID: Int = -1, var createTime: Date = Date(), var stationName: String = "", var communityName: String = "")

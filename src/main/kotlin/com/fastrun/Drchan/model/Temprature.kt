package com.fastrun.TempCollection.model

import java.util.*

/* 温度信息 */
data class Temprature(var id: Int = -1, var deviceSN: String = "", var deviceID: Int = -1, var level: Float = 0f, var temp1: Float = 0f, var temp2: Float = 0f, var statusCode: String = "", var addTime: Date = Date(), var deviceInstalled: Int = 0, var deviceWorkState: Int = 0, var deviceStatus: Int = 0, var deviceCreateTime: Date? = null, var typeName: String = "", var customerID: Int = 0, var customerName: String = "", var phoneNumber: String = "", var positionID: Int = 0, var subNumber: String = "", var floorNumber: String = "", var apartNumber: String = "", var roomName: String = "", var exactPosition: String = "", var buildingID: Int = 0, var buildingName: String = "", var buildingNo: String = "", var communityID: Int = 0, var communityName: String = "", var hesID: Int = 0, var hesName: String = "", var companyID: Int = 0, var companyName: String = "")

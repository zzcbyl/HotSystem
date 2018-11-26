package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Deviceinstall(var id: Int = -1, var positionID: Int = -1, var deviceID: Int = -1, var installed: Int = 0, var workState: Int = 1, var status: Int = 1, var createrID: Int = 0, var createTime: Date = Date(), var deviceSN: String = "", var equipmentChipID: String = "", var communicationCard: String = "", var equipmentPhone: String = "", var remark: String = "", var typeName: String = "", var parameter1: Double = 0.0, var parameter2: Double = 0.0, var interlave: Int = 0, var level: Float = 0f, var customerID: Int = 0, var customerName: String = "", var phoneNumber: String = "", var subNumber: String = "", var floorNumber: String = "", var apartNumber: String = "", var roomName: String = "", var exactPosition: String = "", var buildingID: Int = 0, var buildingName: String = "", var buildingNo: String = "", var communityID: Int = 0, var communityName: String = "", var hesID: Int = 0, var hesName: String = "", var companyID: Int = 0, var companyName: String = "")

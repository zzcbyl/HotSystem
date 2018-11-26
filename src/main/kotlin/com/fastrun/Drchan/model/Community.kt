package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Community(var id: Int = -1, var name: String = "", var stationID: Int = -1, var status: Int = -1, var createTime: Date = Date(), var communityID: Int = 0, var regionalID: Int = 0, var heatingArea: Float = 0f, var mapX: Float = 0f, var mapY: Float = 0f, var communityType: Int = 0, var communityAddress: String = "", var houseHoldCount: Int = 0, var remark: String = "", var updateTime: Date? = null, var stationName: String = "", var companyID: Int = 0, var companyName: String = "")

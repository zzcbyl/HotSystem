package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Community(var id: Int = -1, var name: String = "", var stationID: Int = -1, var status: Int = -1, var createTime: Date = Date(), var stationName: String = "")

package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Temprature(var id: Int = -1, var deviceSN: String = "", var deviceID: Int = -1, var level: Float, var temp1: Float, var temp2: Float, var statusCode: String = "", var addTime: Date = Date())

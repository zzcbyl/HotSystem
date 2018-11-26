package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Devicemonitor(var id: Int = -1, var deviceID: Int = -1, var positionID: Int = -1, var alarmType: Int = -1, var alarmTime: Date = Date(), var temp: Double, var result: String = "")

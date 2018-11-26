package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Device(var id: Int = -1, var deviceSN: String = "", var deviceType: Int = -1, var typeName: String = "", var parameter1: Double, var parameter2: Double, var interlave: Int = -1, var firmwareVersion: String = "", var status: Int = -1, var createrID: Int = -1, var createTime: Date = Date())

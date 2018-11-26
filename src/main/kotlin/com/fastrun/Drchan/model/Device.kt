package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Device(var id: Int = -1, var deviceSN: String = "", var deviceType: Int = -1, var typeName: String = "", var parameter1: Double = 0.0, var parameter2: Double = 0.0, var interlave: Int = 0, var firmwareVersion: String = "", var status: Int = 0, var createrID: Int = 0, var createTime: Date = Date(), var temp1: Float = 0f, var temp2: Float = 0f, var level: Float = 0f, var collectTime: Date? = null, var equipmentChipID: String = "", var communicationCard: String = "", var equipmentPhone: String = "", var remark: String = "", var updateTime: Date? = null, var deviceInitialTemp: Float = 0f)
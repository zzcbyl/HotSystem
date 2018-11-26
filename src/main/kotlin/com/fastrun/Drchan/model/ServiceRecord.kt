package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class ServiceRecord(var id: Long = -1, var serviceTaskID: Long = -1, var from: String = "", var to: String = "", var taskTime: Date = Date(), var state: Int = -1, var userID: Long = -1)

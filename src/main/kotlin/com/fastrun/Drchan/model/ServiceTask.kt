package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class ServiceTask(var id: Long = -1, var userID: Long = -1, var customerMobile: String = "", var startTime: Date = Date(), var endTime: Date? = null, var frequency: Int = -1, var state: Int = -1, var createTime: Date = Date())

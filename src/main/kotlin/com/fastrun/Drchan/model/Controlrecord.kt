package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Controlrecord(var id: Int = -1, var deviceID: Int = -1, var controlID: Int = -1, var remoteCode: String = "", var note: String = "", var sendTime: Date = Date(), var returnStatus: String = "", var executeStatus: Int = 0, var deviceSN: String = "")

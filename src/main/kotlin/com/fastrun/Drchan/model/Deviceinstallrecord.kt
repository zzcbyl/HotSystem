package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Deviceinstallrecord(var id: Int = -1, var positionID: Int = -1, var deviceID: Int = -1, var installed: Int = -1, var workState: Int = -1, var status: Int = -1, var createrID: Int = -1, var createTime: Date = Date())

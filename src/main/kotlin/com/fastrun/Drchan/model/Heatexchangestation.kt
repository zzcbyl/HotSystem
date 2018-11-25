package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Heatexchangestation(var id: Int = -1, var name: String = "", var companyID: Int = -1, var lati: Double = 0.0, var longi: Double = 0.0, var height: Float = 0f, var rsv1: String = "", var rsv2: String = "", var status: Int = -1, var createrID: Int = -1, var createTime: Date = Date(), var parentID: Int = 0, var companyName: String = "", var parentName: String = "")

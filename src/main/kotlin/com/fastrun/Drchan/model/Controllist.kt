package com.fastrun.TempCollection.model

import java.util.*

/* 控制命令 */
data class Controllist(var id: Int = -1, var rangeType: Int = -1, var rangeObjectID: String = "", var remoteCode: String = "", var note: String = "", var status: Int = -1, var createTime: Date = Date(), var executeStatus: Int = 0, var name: String = "", var deviceColumn: String = "", var deviceSN: String = "", var communityName: String = "", var stationName: String = "")

package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Alarmconfig(var id: Int = -1, var lowAlarm2: Double, var lowAlarm1: Double, var highAlarm1: Double, var highAlarm2: Double, var color_low2: String = "", var color_low1: String = "", var color_Normal: String = "", var color_High1: String = "", var color_High2: String = "")

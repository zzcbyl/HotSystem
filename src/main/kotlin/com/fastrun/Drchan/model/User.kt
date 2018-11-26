package com.fastrun.TempCollection.model

import org.springframework.stereotype.Component
import java.util.*

//@Component
data class User(var id: Long = -1, var account: String = "", var password: String = "", var balance: Double = 0.00, var realName: String = "", var type: Int = 1, var mobile: String = "", var qq: String = "", var weixin: String = "", var createTime: Date = Date(), var state: Int = 1, var mobileBalance: Double = 0.0, var startTime: Date? = null, var endTime: Date? = null, var availableLength: Int = -1)
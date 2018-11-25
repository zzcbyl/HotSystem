package com.fastrun.TempCollection.model

import java.util.*

data class Company(var id: Int = -1, var name: String = "", var address: String = "", var contact: String = "", var contactWay: String = "", var parentID: Int = -1, var status: Int = -1, var createTime: Date = Date(), var parentCompanyName: String = "")

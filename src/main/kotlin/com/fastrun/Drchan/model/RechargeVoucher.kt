package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class RechargeVoucher(var id: Long = -1, var userID: Long = -1, var account: String = "", var amount: Double = 0.0, var voucherImgPath: String = "", var createTime: Date = Date(), var state: Int? = null, var note: String = "")

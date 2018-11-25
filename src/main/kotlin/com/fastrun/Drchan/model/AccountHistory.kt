package com.fastrun.TempCollection.model

import java.util.*
/* 消费充值记录 */
data class AccountHistory(var id:Long=-1,var type:Int=0,var amount:Double=0.00,var userId:Long=-1,var note:String="",var createTime:Date=Date(),var createrId:Long=-1,var createrName:String="",var state:Int=1,var relationId:Long=-1,var accountBalance:Double=0.0)

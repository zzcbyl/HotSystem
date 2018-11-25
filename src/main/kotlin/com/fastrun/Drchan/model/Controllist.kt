package com.fastrun.TempCollection.model
import java.util.*
/* 消费充值记录 */
data class Controllist(var id:Int=-1,var rangeType:Int=-1,var rangeObjectID:String="",var remoteCode:String="",var note:String="",var status:Int=-1,var createTime:Date=Date())

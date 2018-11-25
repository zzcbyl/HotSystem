package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Heatexchangestation
import org.apache.ibatis.annotations.Param

interface HeatexchangestationService {

    fun insert(model: Heatexchangestation): Int?

    fun update(model: Heatexchangestation): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Heatexchangestation?

    /*
    * 获取记录数
    * */
    fun getCount(name: String, companyID: Int, parentID: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(name: String, companyID: Int, parentID: Int, offset: Int, pageSize: Int, orderBy: String): List<Heatexchangestation>?

    fun getByName(name: String, companyID: Int): Heatexchangestation?
}

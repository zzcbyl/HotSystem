package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.RechargeVoucher
import org.apache.ibatis.annotations.*

@Mapper
interface RechargeVoucherMapper {

    fun insert(@Param("model") model: RechargeVoucher): Int

    fun update(@Param("model") model: RechargeVoucher): Int

    fun get(@Param("id") id: Int): RechargeVoucher

    fun getPaging(@Param("userID") userID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<RechargeVoucher>

    fun getCount(@Param("userID") userID: Int): Int

    fun delete(@Param("id") id: Int): Int

}

package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.RechargeVoucher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.RechargeVoucherMapper
import org.apache.ibatis.annotations.Param

@Service
class RechargeVoucherServiceImpl: RechargeVoucherService {
    @Resource
    var rechargeVoucherRepository: RechargeVoucherMapper?=null

    @Transactional(propagation =Propagation.REQUIRED)
    override fun insert(model: RechargeVoucher): Int?
    {
        return rechargeVoucherRepository?.insert(model)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun update(model:RechargeVoucher): Int?
    {
        return rechargeVoucherRepository?.update(model)
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    override fun get(id: Int): RechargeVoucher?
    {
        return rechargeVoucherRepository?.get(id)
    }
    
    @Transactional(propagation =Propagation.REQUIRED)
    override fun getPaging(userID:Int,offset:Int,pageSize:Int,orderBy:String): List<RechargeVoucher>?
    {
        return rechargeVoucherRepository?.getPaging(userID,offset,pageSize,orderBy)
    }

    @Transactional(propagation =Propagation.SUPPORTS)
    override fun getCount(userID:Int): Int?
    {
        return rechargeVoucherRepository?.getCount(userID)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun delete(id:Int): Int?
    {
        return rechargeVoucherRepository?.delete(id)
    }

}


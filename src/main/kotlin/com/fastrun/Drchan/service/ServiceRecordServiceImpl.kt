package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.ServiceRecord
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.ServiceRecordMapper
import org.apache.ibatis.annotations.Param

@Service
class ServiceRecordServiceImpl: ServiceRecordService {
    @Resource
    var serviceRecordRepository: ServiceRecordMapper?=null

    @Transactional(propagation =Propagation.REQUIRED)
    override fun insert(model: ServiceRecord): Int?
    {
        return serviceRecordRepository?.insert(model)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun update(model:ServiceRecord): Int?
    {
        return serviceRecordRepository?.update(model)
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    override fun get(id: Int): ServiceRecord?
    {
        return serviceRecordRepository?.get(id)
    }
    
    @Transactional(propagation =Propagation.REQUIRED)
    override fun getPaging(offset:Int,pageSize:Int,orderBy:String): List<ServiceRecord>?
    {
        return serviceRecordRepository?.getPaging(offset,pageSize,orderBy)
    }

    @Transactional(propagation =Propagation.SUPPORTS)
    override fun getCount(): Int?
    {
        return serviceRecordRepository?.getCount()
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun delete(id:Int): Int?
    {
        return serviceRecordRepository?.delete(id)
    }

}


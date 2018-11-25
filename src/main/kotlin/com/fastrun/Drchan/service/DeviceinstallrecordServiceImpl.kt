package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Deviceinstallrecord
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.DeviceinstallrecordMapper
import org.apache.ibatis.annotations.Param

@Service
class DeviceinstallrecordServiceImpl: DeviceinstallrecordService {
    @Resource
    var deviceinstallrecordRepository: DeviceinstallrecordMapper?=null

    @Transactional(propagation =Propagation.REQUIRED)
    override fun insert(model: Deviceinstallrecord): Int?
    {
        return deviceinstallrecordRepository?.insert(model)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun update(model:Deviceinstallrecord): Int?
    {
        return deviceinstallrecordRepository?.update(model)
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    override fun get(id: Int): Deviceinstallrecord?
    {
        return deviceinstallrecordRepository?.get(id)
    }
    
    @Transactional(propagation =Propagation.REQUIRED)
    override fun getPaging(offset:Int,pageSize:Int,orderBy:String): List<Deviceinstallrecord>?
    {
        return deviceinstallrecordRepository?.getPaging(offset,pageSize,orderBy)
    }

    @Transactional(propagation =Propagation.SUPPORTS)
    override fun getCount(): Int?
    {
        return deviceinstallrecordRepository?.getCount()
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun delete(id:Int): Int?
    {
        return deviceinstallrecordRepository?.delete(id)
    }

}


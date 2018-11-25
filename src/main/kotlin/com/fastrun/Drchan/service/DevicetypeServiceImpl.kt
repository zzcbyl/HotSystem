package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Devicetype
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.DevicetypeMapper
import org.apache.ibatis.annotations.Param

@Service
class DevicetypeServiceImpl: DevicetypeService {
    @Resource
    var devicetypeRepository: DevicetypeMapper?=null

    @Transactional(propagation =Propagation.REQUIRED)
    override fun insert(model: Devicetype): Int?
    {
        return devicetypeRepository?.insert(model)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun update(model:Devicetype): Int?
    {
        return devicetypeRepository?.update(model)
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    override fun get(id: Int): Devicetype?
    {
        return devicetypeRepository?.get(id)
    }
    
    @Transactional(propagation =Propagation.REQUIRED)
    override fun getPaging(offset:Int,pageSize:Int,orderBy:String): List<Devicetype>?
    {
        return devicetypeRepository?.getPaging(offset,pageSize,orderBy)
    }

    @Transactional(propagation =Propagation.SUPPORTS)
    override fun getCount(): Int?
    {
        return devicetypeRepository?.getCount()
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun delete(id:Int): Int?
    {
        return devicetypeRepository?.delete(id)
    }

}


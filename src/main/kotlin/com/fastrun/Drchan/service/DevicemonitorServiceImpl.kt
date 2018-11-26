package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.DevicemonitorMapper
import com.fastrun.TempCollection.model.Devicemonitor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class DevicemonitorServiceImpl : DevicemonitorService {
    @Resource
    var devicemonitorRepository: DevicemonitorMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Devicemonitor): Int? {
        return devicemonitorRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Devicemonitor): Int? {
        return devicemonitorRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Devicemonitor? {
        return devicemonitorRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(offset: Int, pageSize: Int, orderBy: String): List<Devicemonitor>? {
        return devicemonitorRepository?.getPaging(offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(): Int? {
        return devicemonitorRepository?.getCount()
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return devicemonitorRepository?.delete(id)
    }

}


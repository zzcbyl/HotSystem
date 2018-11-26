package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.DeviceMapper
import com.fastrun.TempCollection.model.Device
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource


@Service
class DeviceServiceImpl : DeviceService {
    @Resource
    var deviceRepository: DeviceMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Device): Int? {
        return deviceRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Device): Int? {
        return deviceRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Device? {
        return deviceRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(deviceSN: String, level1: Float, level2: Float, offset: Int, pageSize: Int, orderBy: String): List<Device>? {
        return deviceRepository?.getPaging(deviceSN, level1, level2, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(deviceSN: String, level1: Float, level2: Float): Int? {
        return deviceRepository?.getCount(deviceSN, level1, level2)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return deviceRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getBySN(deviceSN: String): Device? {
        return deviceRepository?.getBySN(deviceSN)
    }
}


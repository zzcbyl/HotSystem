package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.DeviceinstallMapper
import com.fastrun.TempCollection.model.Deviceinstall
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class DeviceinstallServiceImpl : DeviceinstallService {
    @Resource
    var deviceinstallrecordRepository: DeviceinstallMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Deviceinstall): Int? {
        return deviceinstallrecordRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Deviceinstall): Int? {
        return deviceinstallrecordRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Deviceinstall? {
        return deviceinstallrecordRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(deviceSN: String, customerName: String, phoneNumber: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int, roomID: Int, offset: Int, pageSize: Int, orderBy: String): List<Deviceinstall>? {
        return deviceinstallrecordRepository?.getPaging(deviceSN, customerName, phoneNumber, companyID, stationID, communityID, buildingID, roomID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(deviceSN: String, customerName: String, phoneNumber: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int, roomID: Int): Int? {
        return deviceinstallrecordRepository?.getCount(deviceSN, customerName, phoneNumber, companyID, stationID, communityID, buildingID, roomID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return deviceinstallrecordRepository?.delete(id)
    }

}


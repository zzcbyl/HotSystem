package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.TempratureMapper
import com.fastrun.TempCollection.model.Temprature
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.Resource

@Service
class TempratureServiceImpl : TempratureService {
    @Resource
    var tempratureRepository: TempratureMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Temprature): Int? {
        return tempratureRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Temprature): Int? {
        return tempratureRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Temprature? {
        return tempratureRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(positionID: Int, deviceSN: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int, customerName: String, phoneNumber: String, minTemp: Int, maxTemp: Int, offset: Int, pageSize: Int, orderBy: String): List<Temprature>? {
        return tempratureRepository?.getPaging(positionID, deviceSN, companyID, stationID, communityID, buildingID, customerName, phoneNumber, minTemp, maxTemp, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(positionID: Int, deviceSN: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int, customerName: String, phoneNumber: String, minTemp: Int, maxTemp: Int): Int? {
        return tempratureRepository?.getCount(positionID, deviceSN, companyID, stationID, communityID, buildingID, customerName, phoneNumber, minTemp, maxTemp)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(deviceID: Int, positionID: Int, beginDate: Date, endDate: Date, offset: Int, pageSize: Int, orderBy: String): List<Temprature>? {
        return tempratureRepository?.getPaging1(deviceID, positionID, beginDate, endDate, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(deviceID: Int, positionID: Int, beginDate: Date, endDate: Date): Int? {
        return tempratureRepository?.getCount1(deviceID, positionID, beginDate, endDate)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return tempratureRepository?.delete(id)
    }

}


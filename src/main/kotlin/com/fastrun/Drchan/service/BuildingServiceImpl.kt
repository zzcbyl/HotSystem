package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.BuildingMapper
import com.fastrun.TempCollection.model.Building
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class BuildingServiceImpl : BuildingService {
    @Resource
    var buildingRepository: BuildingMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Building): Int? {
        return buildingRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Building): Int? {
        return buildingRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Building? {
        return buildingRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(name: String, companyID: Int, stationID: Int, communityID: Int, offset: Int, pageSize: Int, orderBy: String): List<Building>? {
        return buildingRepository?.getPaging(name, companyID, stationID, communityID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(name: String, companyID: Int, stationID: Int, communityID: Int): Int? {
        return buildingRepository?.getCount(name, companyID, stationID, communityID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return buildingRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getByName(name: String, stationID: Int, communityID: Int): Building? {
        return buildingRepository?.getByName(name, stationID, communityID)
    }
}


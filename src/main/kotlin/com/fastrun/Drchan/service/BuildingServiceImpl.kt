package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Building
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.BuildingMapper
import org.apache.ibatis.annotations.Param

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
    override fun getPaging(name: String, stationID: Int, communityID: Int, offset: Int, pageSize: Int, orderBy: String): List<Building>? {
        return buildingRepository?.getPaging(name, stationID, communityID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(name: String, stationID: Int, communityID: Int): Int? {
        return buildingRepository?.getCount(name, stationID, communityID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return buildingRepository?.delete(id)
    }

}


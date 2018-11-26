package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.PositionMapper
import com.fastrun.TempCollection.model.Position
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource


@Service
class PositionServiceImpl : PositionService {
    @Resource
    var positionRepository: PositionMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Position): Int? {
        return positionRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Position): Int? {
        return positionRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Position? {
        return positionRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(positionID: Int, customerName: String, companyID: Int, stationID: Int, heatExchangerUnitID: Int, communityID: Int, buildingID: Int, subNumber: Int, floorNumber: Int, apartNumber: Int, roomName: String, offset: Int, pageSize: Int, orderBy: String): List<Position>? {
        return positionRepository?.getPaging(positionID, customerName, companyID, stationID, heatExchangerUnitID, communityID, buildingID, subNumber, floorNumber, apartNumber, roomName, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(positionID: Int, customerName: String, companyID: Int, stationID: Int, heatExchangerUnitID: Int, communityID: Int, buildingID: Int, subNumber: Int, floorNumber: Int, apartNumber: Int, roomName: String): Int? {
        return positionRepository?.getCount(positionID, customerName, companyID, stationID, heatExchangerUnitID, communityID, buildingID, subNumber, floorNumber, apartNumber, roomName)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return positionRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getByName(communityID: Int, buildingID: Int, subNumber: Int, floorNumber: Int, apartNumber: Int, roomName: String): Position? {
        return positionRepository?.getByName(communityID, buildingID, subNumber, floorNumber, apartNumber, roomName)
    }
}


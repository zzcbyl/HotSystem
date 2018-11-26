package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.CommunityMapper
import com.fastrun.TempCollection.model.Community
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class CommunityServiceImpl : CommunityService {
    @Resource
    var communityRepository: CommunityMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Community): Int? {
        return communityRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Community): Int? {
        return communityRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Community? {
        return communityRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(name: String, companyID: Int, stationID: Int, offset: Int, pageSize: Int, orderBy: String): List<Community>? {
        return communityRepository?.getPaging(name, companyID, stationID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(name: String, companyID: Int, stationID: Int): Int? {
        return communityRepository?.getCount(name, companyID, stationID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return communityRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getByName(name: String, stationID: Int): Community? {
        return communityRepository?.getByName(name, stationID)
    }
}

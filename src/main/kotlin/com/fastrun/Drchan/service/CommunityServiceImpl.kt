package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Community
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.CommunityMapper
import org.apache.ibatis.annotations.Param

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
    override fun getPaging(name: String, stationID: Int, offset: Int, pageSize: Int, orderBy: String): List<Community>? {
        return communityRepository?.getPaging(name, stationID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(name: String, stationID: Int): Int? {
        return communityRepository?.getCount(name, stationID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return communityRepository?.delete(id)
    }

}


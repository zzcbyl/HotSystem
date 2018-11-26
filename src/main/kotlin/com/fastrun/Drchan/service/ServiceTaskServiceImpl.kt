package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.ServiceTaskMapper
import com.fastrun.TempCollection.model.ServiceTask
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class ServiceTaskServiceImpl : ServiceTaskService {
    @Resource
    var serviceTaskRepository: ServiceTaskMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: ServiceTask): Int? {
        return serviceTaskRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: ServiceTask): Int? {
        return serviceTaskRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): ServiceTask? {
        return serviceTaskRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(userId: Int, offset: Int, pageSize: Int, orderBy: String): List<ServiceTask>? {
        return serviceTaskRepository?.getPaging(userId, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(userId: Int): Int? {
        return serviceTaskRepository?.getCount(userId)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return serviceTaskRepository?.delete(id)
    }

}


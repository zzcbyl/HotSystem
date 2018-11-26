package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.TempratureMapper
import com.fastrun.TempCollection.model.Temprature
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
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
    override fun getPaging(offset: Int, pageSize: Int, orderBy: String): List<Temprature>? {
        return tempratureRepository?.getPaging(offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(): Int? {
        return tempratureRepository?.getCount()
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return tempratureRepository?.delete(id)
    }

}


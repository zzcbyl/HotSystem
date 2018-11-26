package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.AlarmconfigMapper
import com.fastrun.TempCollection.model.Alarmconfig
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class AlarmconfigServiceImpl : AlarmconfigService {
    @Resource
    var alarmconfigRepository: AlarmconfigMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Alarmconfig): Int? {
        return alarmconfigRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Alarmconfig): Int? {
        return alarmconfigRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Alarmconfig? {
        return alarmconfigRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(offset: Int, pageSize: Int, orderBy: String): List<Alarmconfig>? {
        return alarmconfigRepository?.getPaging(offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(): Int? {
        return alarmconfigRepository?.getCount()
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return alarmconfigRepository?.delete(id)
    }

}


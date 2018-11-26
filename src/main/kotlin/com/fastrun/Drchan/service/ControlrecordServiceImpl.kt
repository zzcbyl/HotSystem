package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.ControlrecordMapper
import com.fastrun.TempCollection.model.Controlrecord
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource


@Service
class ControlrecordServiceImpl : ControlrecordService {
    @Resource
    var controlrecordRepository: ControlrecordMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Controlrecord): Int? {
        return controlrecordRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Controlrecord): Int? {
        return controlrecordRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Controlrecord? {
        return controlrecordRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(controlID: Int, offset: Int, pageSize: Int, orderBy: String): List<Controlrecord>? {
        return controlrecordRepository?.getPaging(controlID, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(controlID: Int): Int? {
        return controlrecordRepository?.getCount(controlID)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return controlrecordRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun deleteByControlID(controlID: Int): Int? {
        return controlrecordRepository?.deleteByControlID(controlID)
    }
}


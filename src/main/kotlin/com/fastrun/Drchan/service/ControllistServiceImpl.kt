package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.ControllistMapper
import com.fastrun.TempCollection.model.Controllist
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class ControllistServiceImpl : ControllistService {
    @Resource
    var controllistRepository: ControllistMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: Controllist): Int? {
        return controllistRepository?.insert(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: Controllist): Int? {
        return controllistRepository?.update(model)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): Controllist? {
        return controllistRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(offset: Int, pageSize: Int, orderBy: String): List<Controllist>? {
        return controllistRepository?.getPaging(offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(): Int? {
        return controllistRepository?.getCount()
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return controllistRepository?.delete(id)
    }

}


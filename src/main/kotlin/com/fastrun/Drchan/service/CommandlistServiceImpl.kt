package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.CommandlistMapper
import com.fastrun.TempCollection.model.Commandlist
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class CommandlistServiceImpl: CommandlistService {
    @Resource
    var commandlistRepository: CommandlistMapper?=null

    @Transactional(propagation =Propagation.REQUIRED)
    override fun insert(model: Commandlist): Int?
    {
        return commandlistRepository?.insert(model)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun update(model:Commandlist): Int?
    {
        return commandlistRepository?.update(model)
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    override fun get(id: Int): Commandlist?
    {
        return commandlistRepository?.get(id)
    }
    
    @Transactional(propagation =Propagation.REQUIRED)
    override fun getPaging(offset:Int,pageSize:Int,orderBy:String): List<Commandlist>?
    {
        return commandlistRepository?.getPaging(offset,pageSize,orderBy)
    }

    @Transactional(propagation =Propagation.SUPPORTS)
    override fun getCount(): Int?
    {
        return commandlistRepository?.getCount()
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun delete(id:Int): Int?
    {
        return commandlistRepository?.delete(id)
    }

}


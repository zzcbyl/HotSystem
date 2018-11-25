package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Room
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource
import com.fastrun.TempCollection.dal.RoomMapper
import org.apache.ibatis.annotations.Param

@Service
class RoomServiceImpl: RoomService {
    @Resource
    var roomRepository: RoomMapper?=null

    @Transactional(propagation =Propagation.REQUIRED)
    override fun insert(model: Room): Int?
    {
        return roomRepository?.insert(model)
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun update(model:Room): Int?
    {
        return roomRepository?.update(model)
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    override fun get(id: Int): Room?
    {
        return roomRepository?.get(id)
    }
    
    @Transactional(propagation =Propagation.REQUIRED)
    override fun getPaging(offset:Int,pageSize:Int,orderBy:String): List<Room>?
    {
        return roomRepository?.getPaging(offset,pageSize,orderBy)
    }

    @Transactional(propagation =Propagation.SUPPORTS)
    override fun getCount(): Int?
    {
        return roomRepository?.getCount()
    }

    @Transactional(propagation =Propagation.REQUIRED)
    override fun delete(id:Int): Int?
    {
        return roomRepository?.delete(id)
    }

}


package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.AccountHistoryMapper
import com.fastrun.TempCollection.dal.UserMapper
import com.fastrun.TempCollection.model.AccountHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class AccountHistoryServiceImpl : AccountHistoryService {
    @Resource
    var modelRepository: AccountHistoryMapper? = null
    @Resource
    var userRepository: UserMapper? = null

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insert(model: AccountHistory): Int? {
        var result = userRepository?.changBalance(model.userId, model.amount)
        if (result!! > 0) {
            var user = userRepository?.get(model.userId)
            model.accountBalance = user?.balance!!
            return modelRepository?.insert(model)
        }
        return 0
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(model: AccountHistory): Int? {
        return modelRepository?.update(model)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return modelRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Int): AccountHistory? {
        return modelRepository?.get(id)
    }

    /*
    * 获取记录数
    * */
    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(userId: Long, type: Int): Int? {
        return modelRepository?.getCount(userId, type)
    }

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getPaging(userId: Long, type: Int, offset: Int, pageSize: Int, orderBy: String): List<AccountHistory>? {
        return modelRepository?.getPaging(userId, type, offset, pageSize, orderBy)
    }


}

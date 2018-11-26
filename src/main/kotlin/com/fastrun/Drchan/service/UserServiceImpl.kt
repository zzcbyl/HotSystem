package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.dal.UserMapper
import com.fastrun.TempCollection.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class UserServiceImpl : UserService {
    @Resource
    var userRepository: UserMapper? = null

    /*  @Transactional(propagation= Propagation.SUPPORTS)
      override fun getAllPart(): List<UserPart>? {
          return userRepository?.getAllPart()
      }*/

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun findByUserName(realName: String): List<User>? {
        return userRepository?.findByUserName(realName)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(user: User): Int? {
        return userRepository?.update(user)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun getPaging(realName: String, offset: Int, pageSize: Int, orderBy: String): List<User>? {
        return userRepository?.getPaging(realName, offset, pageSize, orderBy)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun insertUser(user: User): Int? {
        return userRepository?.insertUser(user)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun findByAccount(account: String): User? {
        return userRepository?.findByAccount(account)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(id: Int): Int? {
        return userRepository?.delete(id)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun getCount(realName: String): Int? {
        return userRepository?.getCount(realName)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun changPassword(id: Long, password: String): Int? {
        return userRepository?.changPassword(id, password)
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    override fun get(id: Long): User? {
        return userRepository?.get(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun changBalance(id: Long, money: Double): Int? {
        return userRepository?.changBalance(id, money)
    }

}

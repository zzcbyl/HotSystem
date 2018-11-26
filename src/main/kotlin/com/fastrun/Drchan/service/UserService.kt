package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.User

interface UserService {
    /* fun getAllPart(): List<UserPart>?*/

    fun findByUserName(realName: String): List<User>?

    fun findByAccount(account: String): User?

    fun update(user: User): Int?

    fun getPaging(realName: String, offset: Int, pageSize: Int, orderBy: String): List<User>?

    fun insertUser(user: User): Int?

    fun delete(id: Int): Int?

    fun getCount(realName: String): Int?

    fun changPassword(id: Long, password: String): Int?

    fun get(id: Long): User?

    fun changBalance(id: Long, money: Double): Int?

}
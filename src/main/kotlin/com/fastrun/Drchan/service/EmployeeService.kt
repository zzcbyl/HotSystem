package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Employee

interface EmployeeService {

    fun insert(model: Employee): Int?

    fun update(model: Employee): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Employee?

    /*
    * 获取记录数
    * */
    fun getCount(account: String, companyID: Int, stationID: Int): Int?

    /*
    * 分页函数
    * userID：用户ID
    * type：消费类型（0：充值，1：消费）
    * offset：起始记录位置
    * pageSize：页容量
    * orderBy：排序条件
    * */
    fun getPaging(account: String, companyID: Int, stationID: Int, offset: Int, pageSize: Int, orderBy: String): List<Employee>?

    fun findByAccount(account: String): Employee?

    fun resetPassword(id: Int, newPassword: String): Int?
}

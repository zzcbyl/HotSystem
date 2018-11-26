package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.Employee
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param


@Mapper
interface EmployeeMapper {

    fun insert(@Param("model") model: Employee): Int

    fun update(@Param("model") model: Employee): Int

    fun get(@Param("id") id: Int): Employee

    fun getPaging(@Param("account") account: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int, @Param("offset") offset: Int, @Param("pageSize") pageSize: Int, @Param("orderBy") orderBy: String): List<Employee>

    fun getCount(@Param("account") account: String, @Param("companyID") companyID: Int, @Param("stationID") stationID: Int): Int

    fun delete(@Param("id") id: Int): Int

    fun findByAccount(@Param("account") account: String): Employee

    fun resetPassword(@Param("id") id: Int, @Param("newPassword") newPassword: String): Int

    fun changPassword(@Param("id") id: Int, @Param("password") password: String): Int

    fun getByName(@Param("account") account: String): Employee
}

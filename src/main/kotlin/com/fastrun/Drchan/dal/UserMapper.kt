package com.fastrun.TempCollection.dal

import com.fastrun.TempCollection.model.User
import com.fastrun.TempCollection.model.UserPart
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper {

/*    @Select("SELECT realName,type FROM USER")
    fun getAllPart(): List<UserPart>*/

    //@Select("SELECT * FROM USER WHERE state=1 and  realName=#{realName}")
    fun findByUserName(@Param("realName") realName: String): List<User>

    //@Select("SELECT * FROM USER WHERE  state=1 and  account = #{account} limit 1")
    fun findByAccount(@Param("account") account: String): User

    //@Update("Update USER set Password=#{user.password},realName=#{user.realName},mobile=#{user.mobile},QQ=#{user.QQ},weixin=#{user.weixin} where id=#{user.id}")
    fun update(@Param("user") user:User):Int

   // @Select("SELECT * FROM USER where realName like '%#{realName}%' limit #{offset},#{pageSize} order by id desc")
    fun getPaging(@Param("realName") realName:String,@Param("offset") offset:Int,@Param("pageSize") pageSize:Int,@Param("orderBy") orderBy:String): List<User>

    //@Insert("INSERT INTO USER(account,PASSWORD,realName,mobile,QQ,weixin,type) VALUES(#{user.account},#{user.password},#{user.realName},#{user.mobile},#{user.QQ},#{user.weixin},#{user.type})")
    fun insertUser(@Param("user") user: User): Int

    //@Select("update USER set state=-1 where id=#{id}")
    fun delete(@Param("id") id:Int): Int

    fun getCount(@Param("realName") realName:String):Int

    fun changPassword(@Param("id") id:Long,@Param("password") password:String):Int

    fun get(@Param("id") id:Long):User

    fun changBalance(@Param("id") id:Long,@Param("money") money:Double):Int


}
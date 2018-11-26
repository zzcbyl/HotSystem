package com.fastrun.TempCollection.service

import com.fastrun.TempCollection.model.Temprature
import java.util.*


interface TempratureService {

    fun insert(model: Temprature): Int?

    fun update(model: Temprature): Int?

    fun delete(id: Int): Int?

    fun get(id: Int): Temprature?

    fun getCount(positionID: Int, deviceSN: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int,  customerName: String,  phoneNumber: String, minTemp: Int, maxTemp: Int): Int?

    fun getPaging(positionID: Int,  deviceSN: String, companyID: Int, stationID: Int, communityID: Int, buildingID: Int,  customerName: String,  phoneNumber: String, minTemp: Int, maxTemp: Int, offset: Int, pageSize: Int, orderBy: String): List<Temprature>?

    fun getPaging(deviceID: Int, positionID: Int, beginDate: Date, endDate: Date, offset: Int, pageSize: Int, orderBy: String): List<Temprature>?

    fun getCount(deviceID: Int, positionID: Int,  beginDate: Date,  endDate: Date): Int?

}

package com.fastrun.TempCollection.model

import java.util.*

/* 消费充值记录 */
data class Building(var id: Int = -1, var name: String = "", var buildingNo: String = "", var communityID: Int = -1, var stationID: Int = -1, var lati1: Double = 0.0, var longi1: Double = 0.0, var lati2: Double = 0.0, var longi2: Double = 0.0, var floorNumber: Int = 0, var height: Double = 0.0, var status: Int = -1, var createrID: Int = -1, var createTime: Date = Date(), var buildingID: String = "", var crewID: Int = 0, var heatingArea: Float = 0f, var buildYear: Int = 0, var buildingStructure: String = "", var energySaving: Int = 0, var measure: Int = 0, var separateControl: Int = 0, var energyConsumptionType: String = "", var ngasstandardID: Int = 0, var buildingType: String = "", var heatMeteringTransformed: Int = 0, var heatMeteringDate: Date? = null, var wallinSulationTransformed: Int = 0, var wallinSulationDate: Date? = null, var address: String = "", var remark: String = "", var updateTime: Date? = null, var stationName: String = "", var communityName: String = "", var companyID: Int = 0, var companyName: String = "")

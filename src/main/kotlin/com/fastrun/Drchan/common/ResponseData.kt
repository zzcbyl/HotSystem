package com.fastrun.TempCollection

class ResponseData {
    constructor()
    {
        error= mutableListOf<String>()
        data= mutableMapOf<String, Any?>()
    }
    var error: kotlin.collections.MutableList<String>;
    var data:MutableMap<String,Any?>
    fun putDataValue(key: String, value: Any?) {
        data.put(key,value)
    }

    companion object  {
        fun putErrValue(err: List<String>): ResponseData {
            var responseData=ResponseData()
            responseData.error.addAll(err)
            return responseData;
        }
        var errKeys="error"
    }

}
package com.manuelmarvelapp.utils

class DataResponse(val data: Any?, val message: String?) {

    companion object{
        fun response(data: Any?, message: String?): DataResponse{
            return DataResponse(data, message)
        }
    }
}
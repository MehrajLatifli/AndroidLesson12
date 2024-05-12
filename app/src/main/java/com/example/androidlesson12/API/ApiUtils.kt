package com.example.androidlesson12.API

class ApiUtils {

    companion object{

        fun createApi():IApiManager{

            return RetrofitClient.retrofit.create(IApiManager::class.java)

        }
    }
}
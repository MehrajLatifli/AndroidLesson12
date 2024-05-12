package com.example.androidlesson12.API

import com.example.androidlesson12.Models.Todo
import com.example.androidlesson12.Models.TodoListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiManager {

    @GET("todos")
    fun getTodo():Call<TodoListResponse>

    @GET("todos/{id}")
    fun getTodoDetail(@Path("id") id:String):Call<Todo>

}
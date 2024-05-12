package com.example.androidlesson12.Models


import com.google.gson.annotations.SerializedName

data class TodoListResponse(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("todos")
    val todos: List<Todo>?,
    @SerializedName("total")
    val total: Int?
)
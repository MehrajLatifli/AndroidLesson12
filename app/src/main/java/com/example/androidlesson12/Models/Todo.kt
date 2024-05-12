package com.example.androidlesson12.Models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    @SerializedName("completed")
    val completed: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("todo")
    val todo: String?,
    @SerializedName("userId")
    val userId: Int?
): Parcelable
{

}
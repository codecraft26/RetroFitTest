package dev.codecraft.retrofittest

import com.google.gson.annotations.SerializedName

data class MyDataItem(
    @SerializedName("body")
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
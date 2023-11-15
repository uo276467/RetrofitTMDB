package com.example.retrofittmdb.remote.model


import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<ResultX>
)
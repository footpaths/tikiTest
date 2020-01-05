package com.example.tikilisthorizital.ApiService

import android.graphics.ColorSpace
import retrofit2.Call
import retrofit2.Retrofit
 import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface TikiApiService {
    @GET("tikivn/android-home-test/v2/keywords.json")
    fun getPhotos(): Call<List<String>>

}
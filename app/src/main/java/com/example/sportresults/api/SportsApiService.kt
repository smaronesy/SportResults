package com.example.sportresults.api

import com.example.sportresults.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Headers
import retrofit2.http.POST

//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
//    .baseUrl(Constants.BASE_URL)
//    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()

interface SportsApiService {
    @Headers("Content-Type: application/json")
    @POST("results")
    suspend fun getResults(): String
}

object ResultsApi {
    val retrofitService: SportsApiService by lazy {
        retrofit.create(SportsApiService::class.java)
    }
}
package com.kodeco.android.countryinfo.types

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RestCountriesService {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}


fun createRetrofitService(): RestCountriesService {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v3.1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    return retrofit.create(RestCountriesService::class.java)
}

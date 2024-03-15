package com.kodeco.android.countryinfo.types

import com.kodeco.android.countryinfo.ui.components.Resource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.Dictionary

interface RestCountriesService {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}




interface ICountriesRepository {
    fun getAllCountries(): Flow<Resource<List<Country>>>
    fun getCountry(countryName: String): Country?
}

class CountriesRepository: ICountriesRepository {

    private var countries: Map<String, Country> = mutableMapOf<String, Country>()

    private fun createRetrofitService(): RestCountriesService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(RestCountriesService::class.java)
    }
    override fun getAllCountries(): Flow<Resource<List<Country>>> = flow {
        val service = createRetrofitService()
        try {
            emit(Resource.Loading())
            val response = service.getAllCountries()
            if (response.isSuccessful && response.body() != null) {
                val localCountries = response.body()
                if (localCountries != null) {
                    emit(Resource.Success(localCountries))
                    countries = localCountries.associateBy { it.name.common }
                }
                else {
                    emit(Resource.Error("Error fetching countries"))
                }

            } else {
                emit(Resource.Error("Error fetching countries: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching countries: ${e.message}"))
        }
    }

    override fun getCountry(countryName: String): Country? {
        if (countries.keys.contains(countryName)) {
            return countries[countryName]
        }
        else {
            return null
        }
    }

}
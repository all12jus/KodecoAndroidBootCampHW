package com.kodeco.android.countryinfo.types

import com.kodeco.android.countryinfo.ui.screens.countryList.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICountriesRepository {
    fun getAllCountries(): Flow<Resource<List<Country>>>
    fun getCountry(countryName: String): Country?
}

class CountriesRepository: ICountriesRepository {

    private var countries: Map<String, Country> = mutableMapOf<String, Country>()

    override fun getAllCountries(): Flow<Resource<List<Country>>> = flow {
        val service = CountriesService().createRetrofitService()
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
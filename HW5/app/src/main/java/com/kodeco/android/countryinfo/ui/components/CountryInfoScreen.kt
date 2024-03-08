package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.types.createRetrofitService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
}


fun getAllCountriesFlow(): Flow<Resource<List<Country>>> = flow {
    val service = createRetrofitService()
    try {
        emit(Resource.Loading())
        val response = service.getAllCountries()
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.Success(response.body()!!))
        } else {
            emit(Resource.Error("Error fetching countries: ${response.message()}"))
        }
    } catch (e: Exception) {
        emit(Resource.Error("Error fetching countries: ${e.message}"))
    }
}

@Composable
fun CountryInfoScreen(countries: List<Country>, errorMessage: String?, isLoading: Boolean) {
        if (isLoading){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Loading",
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
    else {
        if (errorMessage != null && errorMessage!!.isNotEmpty()){
            CountryErrorScreen(errorMessage = errorMessage!!)
        }
        else {
            CountryInfoList(countries = countries)
        }
    }
}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoScreenPreview() { }

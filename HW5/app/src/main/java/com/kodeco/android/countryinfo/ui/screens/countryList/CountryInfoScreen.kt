package com.kodeco.android.countryinfo.ui.screens.countryList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.components.CountryInfoList
import com.kodeco.android.countryinfo.ui.components.Loading

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
}

@Composable
fun CountryInfoScreen(viewModel: CountryInfoViewModel) {
    // Collecting StateFlows from the ViewModel
    val countries by viewModel.countries.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState(initial = false)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = null)

    if (isLoading){
        Loading()
    }
    else {
        if (errorMessage != null && errorMessage!!.isNotEmpty()){
            CountryErrorScreen(errorMessage = errorMessage!!)
        }
        else {
            CountryInfoList(viewModel = viewModel, countries = countries)
        }
    }
}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoScreenPreview() { }

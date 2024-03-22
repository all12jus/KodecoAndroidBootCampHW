package com.kodeco.android.countryinfo.ui.screens.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.kodeco.android.countryinfo.types.ICountriesRepository

class CountryInfoViewModelFactory (
    private val repository: ICountriesRepository,
    private val navController: NavController
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryInfoViewModel(repository, navController) as T
    }
}

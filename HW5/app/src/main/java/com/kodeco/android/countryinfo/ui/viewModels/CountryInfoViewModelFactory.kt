package com.kodeco.android.countryinfo.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider
import com.kodeco.android.countryinfo.StateFlows
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.types.ICountriesRepository
import com.kodeco.android.countryinfo.ui.components.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryInfoViewModelFactory (private val repository: ICountriesRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryInfoViewModel(repository) as T
    }
}


class CountryInfoViewModel(private var repository: ICountriesRepository): ViewModel() {

//    init {
//        viewModelScope.launch {
//            reloadData()
//        }
//    }

    private val _countries = MutableStateFlow(emptyList<Country>())
//    val countries: MutableStateFlow<List<List<Country>>> = _countries
    val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _reloadCount = MutableStateFlow<Int>(0)
    val reloadCount = _reloadCount.asStateFlow()


    fun reloadData() {
        viewModelScope.launch {
            _reloadData()
        }
    }

    private suspend fun _reloadData() {
        _isLoading.value = true
        _countries.value = emptyList()
        _reloadCount.value += 1

//        viewModelScope.launch {
            repository.getAllCountries().collect {
                when (it) {
                    is Resource.Error -> {
                        _errorMessage.value = it.message
                        _isLoading.value = false
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _errorMessage.value = null
                        _isLoading.value = false
                        _countries.value = it.data
                    }
                }
            }

//        }

    }

}
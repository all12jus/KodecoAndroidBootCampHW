package com.kodeco.android.countryinfo.ui.screens.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.types.ICountriesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CountryInfoViewModel(private var repository: ICountriesRepository): ViewModel() {

    init {
        GlobalScope.launch {
            while (true) {
                delay(1_000L)
                _timerFlow.value += 1
            }
        }
    }

    private val _countries = MutableStateFlow(emptyList<Country>())
    val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _reloadCount = MutableStateFlow<Int>(0)
    val reloadCount = _reloadCount.asStateFlow()

    private val _timerFlow = MutableStateFlow<Int>(0)
    val timerFlow = _timerFlow.asStateFlow()

    private val _opens = MutableStateFlow<Int>(0)
    val opens = _opens.asStateFlow()

    private val _closes = MutableStateFlow<Int>(0)
    val closes = _closes.asStateFlow()

    fun reloadData() {
        viewModelScope.launch {
            _reloadData()
        }
    }

    fun incrementCloses() {
        _closes.value += 1
    }

    fun incrementOpens() {
        _opens.value += 1
    }

    private suspend fun _reloadData() {
        _isLoading.value = true
        _countries.value = emptyList()
        _reloadCount.value += 1

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
    }
}
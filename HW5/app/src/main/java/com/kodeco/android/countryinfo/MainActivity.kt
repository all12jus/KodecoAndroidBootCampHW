package com.kodeco.android.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kodeco.android.countryinfo.types.CountriesRepository
import com.kodeco.android.countryinfo.ui.screens.countryList.CountryInfoNavHost
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository  = CountriesRepository()

        setContent {
            MyApplicationTheme {
                CountryInfoNavHost(repository = repository)
            }
        }
    }
}

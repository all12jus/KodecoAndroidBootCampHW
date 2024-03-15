package com.kodeco.android.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kodeco.android.countryinfo.types.CountriesRepository
import com.kodeco.android.countryinfo.ui.components.MainView
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import com.kodeco.android.countryinfo.ui.screens.countryList.CountryInfoViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                MainView(viewModel = viewModel(
                    factory = CountryInfoViewModelFactory(
                        repository = CountriesRepository()
                    )
                ))

            }
        }
    }
}

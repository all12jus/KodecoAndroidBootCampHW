package com.kodeco.android.countryinfo.ui.screens.countryList

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.countryinfo.types.CountriesRepository
import com.kodeco.android.countryinfo.ui.components.AboutScreen
import com.kodeco.android.countryinfo.ui.components.CountryDetailsScreen
import com.kodeco.android.countryinfo.ui.components.CountryDetailsViewModelFactory
import com.kodeco.android.countryinfo.ui.components.CountryErrorScreen
import com.kodeco.android.countryinfo.ui.components.MainView

@Composable
fun CountryInfoNavHost(repository: CountriesRepository) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list",
//        modifier = Modifier.padding(innerPadding)
    ) {

        composable("list") {

            MainView(
                viewModel = viewModel(
                    factory = CountryInfoViewModelFactory(
                        repository = repository,
                        navController = navController
                    )
                )
            )

        }

        composable("country/{countryName}") {
            val countryName = it.arguments?.getString("countryName") ?: error("CountryName is required")
            val country = repository.getCountry(countryName)
            if (country != null) {
                CountryDetailsScreen(
                    country = country,
                    viewModel = viewModel(
                        factory = CountryDetailsViewModelFactory(
                            navController = navController
                        )
                    )
                )
            }
            else {
                CountryErrorScreen(errorMessage = "No Country Name")
            }
        }

        composable("about") {

            AboutScreen(navController = navController)

        }


    }

















}
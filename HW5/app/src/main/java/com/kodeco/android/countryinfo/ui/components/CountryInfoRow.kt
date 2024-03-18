package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.android.countryinfo.types.CountriesRepository
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.ui.screens.countryList.CountryInfoViewModel
import com.kodeco.android.countryinfo.ui.screens.countryList.CountryInfoViewModelFactory

@Composable
fun CountryInfoRow(viewModel: CountryInfoViewModel, country: Country) {
    var isExpanded by remember { mutableStateOf<Boolean>(false) }

    Box (modifier = Modifier
        .fillMaxWidth()
        .border(width = 1.dp, color = Color.Black)) {

        Column {
            Box (modifier = Modifier
                .fillMaxWidth()
                .clickable {
//                    isExpanded = !isExpanded
                    viewModel.onCountryRowTap(country.name.common)
                    // TODO: change this for regular navigation
                }
            ) {
                Column {
                    Text(
                        text = country.name.common,
                        fontWeight = FontWeight.Bold,

                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )


                    if (country.capital?.isNotEmpty() == true) {
                        Text(
                            text = country.capital.first()!!,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                        )
                    }
                }
            }



//            if (isExpanded) {
//                CountryDetailsScreen(country = country, viewModel = viewModel)
//            }

        }


    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoRowPreview() { }

@Composable
fun CountryInfoNavHost(repository: CountriesRepository) {

    val navController = rememberNavController()

//    NavHost(navController = navController, startDestination = "list")

    NavHost(
        navController = navController,
        startDestination = "list",
//        modifier = Modifier.padding(innerPadding)
    ) {

        composable("list") {

            MainView(viewModel = viewModel(
                factory = CountryInfoViewModelFactory(
                    repository = repository,
                    navController = navController
                )

            ))

//            CountryInfoScreen(
//                viewModel = viewModel(
//                    factory = CountryInfoViewModel.CountryInfoViewModelFactory(
//                        repository = repository,
//                    ),
//                ),
//                onCountryRowTap = { countryIndex ->
//                    navController.navigate("details/$countryIndex")
//                }
//            )
        }

        composable("country/{countryName}") {
            val countryName = it.arguments?.getString("countryName") ?: error("CountryName is required")
            val country = repository.getCountry(countryName)
            if (country != null) {
                CountryDetailsScreen(country = country, viewModel = viewModel(
                    factory = CountryDetailsViewModelFactory(
//                        repository = repository,
                        navController = navController
                    )
                ))
            }
            else {
                CountryErrorScreen(errorMessage = "No Country Name")
            }

//            CountryDetailsScreen(country = repository.getCountry(it.arguments.getString("countryName") ?: error("Country Name is required")), viewModel = )
        }


    }

















}
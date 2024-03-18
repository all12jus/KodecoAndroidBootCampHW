package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kodeco.android.countryinfo.types.Country

// TODO fill in the CountryDetailsScreen. NOTE this is above-and-beyond the required
//  section of the homework assignment.
@Composable
fun CountryDetailsScreen(country: Country, viewModel: CountryDetailsViewModel) {
    Column {

        Button(onClick = { viewModel.navigateHome() }) {
            Text(text = "List")

        }

        AsyncImage(
            model = country.flags.png,
            contentDescription = "Flag",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

// TODO fill out the preview. NOTE this is above-and-beyond the required
////  section of the homework assignment.
@Preview
@Composable
fun CountryDetailsScreenPreview() { }


//class CountryDetailsViewModel(private var repository: ICountriesRepository): ViewModel() {
//    fun getCo
//}

class CountryDetailsViewModel(
//    private var repository: ICountriesRepository,
    private val navController: NavController
): ViewModel() {

    fun navigateHome() {
        navController.navigate("list")
    }

}

class CountryDetailsViewModelFactory (
//    private val repository: ICountriesRepository,
    private val navController: NavController
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryDetailsViewModel(navController) as T
    }
}

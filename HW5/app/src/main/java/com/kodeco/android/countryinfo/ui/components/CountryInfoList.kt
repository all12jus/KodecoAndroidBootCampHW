package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.ui.screens.countryList.CountryInfoViewModel

@Composable
fun CountryInfoList(viewModel: CountryInfoViewModel, countries: List<Country>) {
    LazyColumn {
        items(countries) { country ->
            CountryInfoRow(viewModel = viewModel, country = country)
        }
    }
}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoListPreview() { }

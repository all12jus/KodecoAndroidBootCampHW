package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kodeco.android.countryinfo.types.Country

// TODO fill in the CountryDetailsScreen. NOTE this is above-and-beyond the required
//  section of the homework assignment.
@Composable
fun CountryDetailsScreen(country: Country) {
    AsyncImage(model = country.flags.png, contentDescription = "Flag", contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxWidth())
}

// TODO fill out the preview. NOTE this is above-and-beyond the required
////  section of the homework assignment.
@Preview
@Composable
fun CountryDetailsScreenPreview() { }

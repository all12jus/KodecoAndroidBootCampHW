package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountryErrorScreen(errorMessage: String) {
    Text(
        text = errorMessage,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

// TODO fill out this preview.
@Preview
@Composable
fun CountryErrorScreenPreview() { }

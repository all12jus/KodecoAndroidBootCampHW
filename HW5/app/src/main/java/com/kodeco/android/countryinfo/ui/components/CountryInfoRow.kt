package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.types.Country

@Composable
fun CountryInfoRow(country: Country) {

        Box (modifier = Modifier.fillMaxWidth().border(width = 1.dp, color = Color.Black)) {

            Column {
                Text(
                    text = country.name.common,
                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )

                if (country.capital?.isNotEmpty() == true){
                    Text(
                        text = country.capital.first()!!,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                    )
                }
            }


    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoRowPreview() { }

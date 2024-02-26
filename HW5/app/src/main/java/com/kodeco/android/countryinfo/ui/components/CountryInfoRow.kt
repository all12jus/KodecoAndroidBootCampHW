package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kodeco.android.countryinfo.types.Country

@Composable
fun CountryInfoRow(country: Country) {
    var isExpanded by remember { mutableStateOf<Boolean>(false) }

    Box (modifier = Modifier
        .fillMaxWidth()
        .border(width = 1.dp, color = Color.Black)) {

        Column {
            Box (modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
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



            if (isExpanded) {
                AsyncImage(model = country.flags.png, contentDescription = "Flag", contentScale = ContentScale.FillWidth, modifier = Modifier.fillMaxWidth())
            }

        }


    }

}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoRowPreview() { }

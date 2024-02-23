package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.types.createRetrofitService

@Composable
fun CountryInfoScreen() {

    var countries by remember { mutableStateOf<List<Country>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf<Boolean>(true) }

    LaunchedEffect(key1 = true) {  // 'true' as a constant means this effect only runs once
        val service = createRetrofitService()
        try {
            val response = service.getAllCountries()
            if (response.isSuccessful && response.body() != null) {
                countries = response.body()!!
            }
            else {
                errorMessage = "Error Fetching countries ${response.message()}"
            }
        } catch (e: Exception) {
            errorMessage = "Error Fetching countries ${e.message}"
        }

        finally {
            isLoading = false
        }

        println(errorMessage)
        println(countries)


    }


    if (isLoading){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Loading",
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
    else {
        if (errorMessage != null && errorMessage!!.isNotEmpty()){
            CountryErrorScreen(errorMessage = errorMessage!!)
        }
        else {
            CountryInfoList(countries = countries)
        }
    }


}

// TODO fill out the preview.
@Preview
@Composable
fun CountryInfoScreenPreview() { }

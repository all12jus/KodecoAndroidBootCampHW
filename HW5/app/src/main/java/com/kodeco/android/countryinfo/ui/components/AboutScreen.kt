package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kodeco.android.countryinfo.BuildConfig

@Composable
fun AboutScreen(navController: NavController) {
    Column {
        Text(text = "Version: ${BuildConfig.VERSION_NAME}")

        Button(onClick = {
            navController.navigate("list")
        }) {
            Text(text = "Back")
        }
    }
}
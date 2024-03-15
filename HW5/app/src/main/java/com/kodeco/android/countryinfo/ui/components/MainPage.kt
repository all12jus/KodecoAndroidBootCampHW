package com.kodeco.android.countryinfo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.StateFlows
import com.kodeco.android.countryinfo.ui.viewModels.CountryInfoViewModel

@Composable
fun MainView(viewModel: CountryInfoViewModel) {
    val counter by StateFlows.timerFlow.collectAsState()
    val opens by StateFlows.opens.collectAsState()
    val closes by StateFlows.closes.collectAsState()

    val reloadRequested = remember { mutableStateOf(false) }

    val isLoading by viewModel.isLoading.collectAsState()
    val reloadCount by viewModel.reloadCount.collectAsState()

    LaunchedEffect (key1 = true) {
        viewModel.reloadData()
    }

    LaunchedEffect(reloadRequested.value) {
        if (reloadRequested.value) {
            viewModel.reloadData()
            reloadRequested.value = false
        }

    }

    Scaffold(
        topBar = {
            TopAppBar (
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
//                                Text("Small Top App Bar")
                    Row {
                        Text(text = "Timer: $counter Actions: $opens / $closes", modifier = Modifier.padding(8.dp))

                        Button(onClick = {
                            reloadRequested.value = true
                        }, enabled = !isLoading) {
                            Text("Refresh")
                        }
                    }

                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Time Awake: $counter\nReload Count: $reloadCount",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    ) { it ->
        Surface (
            modifier = Modifier.padding(it)
        ) {
            //                Text(text = "Timer: ${counter.toString()}")
            // TODO complete the composable content and provide
            //  models for Country, CountryName, and CountryFlags.
            CountryInfoScreen(
                viewModel = viewModel
            )

        }


    }

}
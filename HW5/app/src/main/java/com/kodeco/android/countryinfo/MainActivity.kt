package com.kodeco.android.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.types.Country
import com.kodeco.android.countryinfo.types.createRetrofitService
import com.kodeco.android.countryinfo.ui.components.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.components.Resource
import com.kodeco.android.countryinfo.ui.components.getAllCountriesFlow
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
object StateFlows {
    var timerFlow = MutableStateFlow(0)

    var opens = MutableStateFlow(0)
    var closes = MutableStateFlow(0)

    // this one helped me figure out that the other way I was doing it was double reloading.
    var reloadCount = MutableStateFlow(0)

    init {
        GlobalScope.launch {
            while (true) {
                delay(1_000L)
                timerFlow.value += 1
            }
        }
    }
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO build out the retrofit service and reference it here.
        //  Pass the service in to the CountryInfoScreen composable below.

        setContent {

            val counter by StateFlows.timerFlow.collectAsState()
            val opens by StateFlows.opens.collectAsState()
            val closes by StateFlows.closes.collectAsState()
            val reloadCount by StateFlows.reloadCount.collectAsState()

            var countries by remember { mutableStateOf<List<Country>>(emptyList()) }
            var errorMessage by remember { mutableStateOf<String?>(null) }
            var isLoading by remember { mutableStateOf<Boolean>(true) }

            val reloadRequested = remember { mutableStateOf(false) }

            suspend fun reloadData() {
//                isLoading = true
                countries = emptyList()
                StateFlows.reloadCount.value += 1
                getAllCountriesFlow().collect {
                    when (it) {
                        is Resource.Error -> {
                            errorMessage = it.message
                            isLoading = false
                        }
                        is Resource.Loading -> {
                            isLoading = true
                        }
                        is Resource.Success -> {
                            errorMessage = null
                            isLoading = false
                            countries = it.data
                        }
                    }
                }
            }

            LaunchedEffect(key1 = true) {  // 'true' as a constant means this effect only runs once
                reloadData()
            }

            LaunchedEffect(reloadRequested.value) {
                if (reloadRequested.value) {
                    reloadData()
                    reloadRequested.value = false
                }

            }

            MyApplicationTheme {

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
                            countries = countries,
                            errorMessage = errorMessage,
                            isLoading = isLoading
                        )

                    }


                }

            }
        }
    }
}

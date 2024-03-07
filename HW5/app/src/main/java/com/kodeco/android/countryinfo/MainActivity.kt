package com.kodeco.android.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kodeco.android.countryinfo.ui.components.CountryInfoScreen
import com.kodeco.android.countryinfo.ui.theme.MyApplicationTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
object StateFlows {
    var timerFlow = MutableStateFlow(0)

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
                                Text(text = "Timer: $counter")
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            Text(
                                text = "Time Awake: $counter",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(8.dp).fillMaxWidth()
                            )
                        }

//                        BottomAppBar (
//                            colors = TopAppBarDefaults.topAppBarColors(
//                                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                                titleContentColor = MaterialTheme.colorScheme.primary,
//                            ),
//                            title = {
////                                Text("Small Top App Bar")
//                                Text(text = "Timer: $counter")
//                            }
//                        )
                    }
                ) { it ->
                    Surface (
                        modifier = Modifier.padding(it)
                    ) {
                        //                Text(text = "Timer: ${counter.toString()}")
                        // TODO complete the composable content and provide
                        //  models for Country, CountryName, and CountryFlags.
                        CountryInfoScreen()
                    }


                }

            }
        }
    }
}

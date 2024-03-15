package com.kodeco.android.countryinfo.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: CountryName,
    val capital: List<String>?,
    val population: Long,
    val area: Double,
    val flags: CountryFlags
)

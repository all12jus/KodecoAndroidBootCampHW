package com.kodeco.android.countryinfo.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryFlags(val png: String)

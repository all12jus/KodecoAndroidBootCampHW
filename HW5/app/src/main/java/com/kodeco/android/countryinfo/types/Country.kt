package com.kodeco.android.countryinfo.types

data class Country(val name: String, val capital: List<String>?, val population: Long, val area: Double, val flags: CountryFlags)

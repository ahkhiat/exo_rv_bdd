package com.devid_academy.exo_rv_bdd.DTO


import com.devid_academy.exo_rv_bdd.DTO.CountryDTO
import com.squareup.moshi.Json

data class GetCountriesDTO(
    @Json(name = "countries")
    val countries: List<CountryDTO>,
    @Json(name = "status")
    val status: String
)
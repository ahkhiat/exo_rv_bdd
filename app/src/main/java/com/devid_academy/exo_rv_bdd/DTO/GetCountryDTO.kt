package com.devid_academy.exo_rv_bdd.DTO


import com.squareup.moshi.Json

data class GetCountryDTO(
    @Json(name = "country")
    val country: CountryDTO,
    @Json(name = "status")
    val status: String
)
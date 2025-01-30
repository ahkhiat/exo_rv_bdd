package com.devid_academy.exo_rv_bdd.DTO


import com.squareup.moshi.Json

data class UpdateCountryDTO(
    @Json(name = "id")
    val id: Long,
    @Json(name = "nom")
    val nom: String,
    @Json(name = "url")
    val url: String
)
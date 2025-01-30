package com.devid_academy.exo_rv_bdd.DTO


import com.squareup.moshi.Json

data class RetourDTO(
    @Json(name = "retour")
    val retour: Int
)
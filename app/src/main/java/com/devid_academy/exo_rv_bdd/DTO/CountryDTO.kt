package com.devid_academy.exo_rv_bdd.DTO

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDTO(
    @Json(name = "id")
    val id: Long,
    @Json(name = "id_stagiaire")
    val idStagiaire: Long,
    @Json(name = "nom")
    val nom: String,
    @Json(name = "url")
    val url: String
) : Parcelable
package com.example.flagsapp.data.remote.dto

data class CountryDto(
    val name: NameDto?,
    val flags: FlagsDto?,
    val capital: List<String>?,
) {

    data class NameDto(
        val common: String?,
        val official: String?
    )

    data class FlagsDto(
        val png: String?,
        val svg: String?
    )
}
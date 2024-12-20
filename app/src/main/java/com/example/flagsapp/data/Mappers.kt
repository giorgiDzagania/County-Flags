package com.example.flagsapp.data

import com.example.flagsapp.data.remote.dto.CountryDto
import com.example.flagsapp.domain.model.Country

fun CountryDto.toCountry() = Country(
    name = this.name?.let { nameDto ->
        Country.Name(
            common = nameDto.common,
            official = nameDto.official
        )
    },
    flags = this.flags?.let { flagsDto ->
        Country.Flags(
            png = flagsDto.png,
            svg = flagsDto.svg
        )
    },
    capital = this.capital
)
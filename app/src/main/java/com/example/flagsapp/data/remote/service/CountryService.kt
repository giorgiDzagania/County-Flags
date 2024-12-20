package com.example.flagsapp.data.remote.service

import com.example.flagsapp.data.remote.dto.CountryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("all?fields=name,capital,currencies,flags")
    suspend fun getAllCountries(): Response<List<CountryDto>>

    @GET("v3.1/name/{commonName}")
    suspend fun getCountryByName(@Path("commonName") commonName: String): Response<List<CountryDto>>

}
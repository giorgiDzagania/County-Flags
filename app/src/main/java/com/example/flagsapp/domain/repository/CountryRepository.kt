package com.example.flagsapp.domain.repository

import com.example.flagsapp.core.OperationStatus
import com.example.flagsapp.domain.model.Country

interface CountryRepository {

    suspend fun getAllCountries(): OperationStatus<List<Country>>

}
package com.example.flagsapp.data.repository

import com.example.flagsapp.core.NetworkClassHelper
import com.example.flagsapp.core.OperationStatus
import com.example.flagsapp.core.map
import com.example.flagsapp.data.remote.service.CountryService
import com.example.flagsapp.data.toCountry
import com.example.flagsapp.domain.model.Country
import com.example.flagsapp.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val service: CountryService
) : CountryRepository {

    override suspend fun getAllCountries(): OperationStatus<List<Country>> {
        return NetworkClassHelper.safeApiCall {
            service.getAllCountries()
        }.map { listOfCountryDto -> listOfCountryDto.map { countryDto -> countryDto.toCountry() } }
    }


}
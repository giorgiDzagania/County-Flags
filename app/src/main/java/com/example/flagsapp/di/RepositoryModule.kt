package com.example.flagsapp.di

import com.example.flagsapp.data.repository.CountryRepositoryImpl
import com.example.flagsapp.domain.repository.CountryRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    single {
        CountryRepositoryImpl(get())
    } bind CountryRepository::class


}
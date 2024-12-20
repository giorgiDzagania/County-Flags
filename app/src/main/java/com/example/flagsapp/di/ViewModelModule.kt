package com.example.flagsapp.di

import com.example.flagsapp.presentation.screens.allCountries.AllCountriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AllCountriesViewModel(get())
    }

}
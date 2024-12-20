package com.example.flagsapp.presentation.screens.allCountries

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagsapp.core.OperationStatus
import com.example.flagsapp.domain.model.Country
import com.example.flagsapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllCountriesViewModel(
    private val repository: CountryRepository
) : ViewModel() {

    private var _allCountries = MutableStateFlow<List<Country>>(emptyList())
    val allCountries: StateFlow<List<Country>> = _allCountries

    private var _progressBar = MutableStateFlow<Boolean>(false)
    val progressBar: StateFlow<Boolean> = _progressBar


    init {
        getAllCountries()
    }

    fun getAllCountries() = viewModelScope.launch {
        _progressBar.emit(true)
        when (val status = repository.getAllCountries()) {
            is OperationStatus.Success -> {
                val countries = status.value
                _allCountries.emit(countries)
            }

            is OperationStatus.Failure -> {
                Log.e("ViewModel", "Error: ${status.exception.message}")
            }
        }
        _progressBar.emit(false)
    }

    fun searchCountries(query: String) = viewModelScope.launch {
        val filteredList = _allCountries.value.filter {
            it.name?.common!!.contains(query, ignoreCase = true)
        }
        _allCountries.emit(filteredList)
    }

    fun resetSearch() = viewModelScope.launch {
        _allCountries.emit(_allCountries.value)
    }
}
package com.example.flagsapp.presentation.screens.countryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.flagsapp.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCountryDetailsBinding
    private val args = navArgs<CountryDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayCurrentCountry()
    }

    private fun displayCurrentCountry() = with(binding) {
        val currentCountry = args.value.currentCountry
        curCountryName.text = currentCountry.name?.common ?: ""
        curCountryCapital.text = currentCountry.capital?.joinToString(", ") ?: ""
        Glide.with(curCountryFlag)
            .load(currentCountry.flags?.png)
            .into(curCountryFlag)
    }

}
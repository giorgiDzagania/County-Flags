package com.example.flagsapp.presentation.screens.allCountries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flagsapp.databinding.ItemLayoutBinding
import com.example.flagsapp.domain.model.Country

class AllCountriesAdapter :
    ListAdapter<Country, AllCountriesAdapter.AllCountriesViewModel>(DiffUtilCallBack()) {

    var onItemClick: (country: Country) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCountriesViewModel {
        return AllCountriesViewModel(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AllCountriesViewModel, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name?.common == newItem.name?.common
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    inner class AllCountriesViewModel(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) = with(binding) {
            Glide.with(countryFlag)
                .load(country.flags?.png)
                .into(countryFlag)

            root.setOnClickListener {
                onItemClick.invoke(country)
            }
        }
    }

}
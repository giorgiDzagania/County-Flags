package com.example.flagsapp.presentation.screens.allCountries

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flagsapp.databinding.FragmentAllCountriesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCountriesFragment : Fragment() {
    private lateinit var binding: FragmentAllCountriesBinding
    private val viewModel by viewModel<AllCountriesViewModel>()
    private val allCountriesAdapter = AllCountriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setCollectors()
        setListeners()
    }

    private fun setListeners() {
        allCountriesAdapter.onItemClick = { country ->
            findNavController().navigate(
                AllCountriesFragmentDirections.actionAllCountriesFragmentToCountryDetailsFragment(
                    country
                )
            )
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString().orEmpty()
                if (query.isEmpty()) {
                    viewModel.getAllCountries()
                } else {
                    viewModel.searchCountries(query)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allCountries.collect { listOfCountries ->
                allCountriesAdapter.submitList(listOfCountries)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.progressBar.collect { isLoading ->
                if (isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewAllCountries.apply {
            adapter = allCountriesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

}
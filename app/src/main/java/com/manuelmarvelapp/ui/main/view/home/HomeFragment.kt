package com.manuelmarvelapp.ui.main.view.home

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.manuelmarvelapp.R
import com.manuelmarvelapp.data.model.marvel.character_response.ResultsItem
import com.manuelmarvelapp.databinding.FragmentHomeBinding
import com.manuelmarvelapp.ui.main.adapter.HeroesAdapater
import com.manuelmarvelapp.ui.main.view.base.BaseFragment
import com.manuelmarvelapp.ui.main.view.home.viewmodel.HomeViewModel
import com.manuelmarvelapp.utils.Status
import com.manuelmarvelapp.utils.toast

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HeroesAdapater
    private val viewModel: HomeViewModel by activityViewModels()

    override fun FragmentHomeBinding.initialize() {
        binding = this
        setRecycler()
        listener()
        observableHeroes()
    }

    private fun setRecycler() {
        adapter = HeroesAdapater {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailHeroeFragment(
                    it
                )
            )
        }

        binding.rvHeroes.adapter = adapter
    }

    private fun listener() {
        binding.arrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observableHeroes() {
        viewModel.getHeroesFromService()
        viewModel.getHeroes().observe(viewLifecycleOwner, {
            binding.resource = it // Set DataBinding

            when (it.status) {
                Status.SUCCESS -> {
                    adapter.heroesList.clear()
                    adapter.addItems((it.data ?: arrayListOf()) as ArrayList<ResultsItem>)
                }

                Status.ERROR -> {
                    toast(it.message ?: "")
                }

                Status.LOADING -> {
                    //Nothing to do
                }
            }
        })
    }
}
package com.manuelmarvelapp.ui.main.view.detail_heroe

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.manuelmarvelapp.R
import com.manuelmarvelapp.databinding.FragmentDetailHeroeBinding
import com.manuelmarvelapp.ui.main.adapter.DetailViewPagerAdapter
import com.manuelmarvelapp.ui.main.view.base.BaseFragment

class DetailHeroeFragment :
    BaseFragment<FragmentDetailHeroeBinding>(R.layout.fragment_detail_heroe) {

    private lateinit var binding: FragmentDetailHeroeBinding
    private lateinit var adapterPagerAdapter: DetailViewPagerAdapter
    private val safeArgs: DetailHeroeFragmentArgs by navArgs()
    private var idCharacter: Int? = null

    override fun FragmentDetailHeroeBinding.initialize() {
        binding = this
        safeArgs.resultItem.let {
            //Set Id
            idCharacter = it?.id ?: 0
            //Set Name
            tvName.text = it?.name ?: getString(R.string.heroe)

            tvDescription.text =
                if ((it?.description ?: "").isNotEmpty()) {
                    it?.description ?: getString(R.string.description)
                } else {
                    getString(R.string.description)
                }
            //Set Image
            Glide.with(requireContext())
                .load(it?.thumbnail?.path + "/portrait_uncanny." + it?.thumbnail?.extension)
                .placeholder(R.drawable.placeholder)
                .into(ivHeroe)
        }

        setViewPagerAdapter()
        listener()
    }

    private fun setViewPagerAdapter() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.comics)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.series)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.stories)))

        adapterPagerAdapter = DetailViewPagerAdapter(childFragmentManager, idCharacter ?: 0)
        binding.viewPagerFragments.adapter = adapterPagerAdapter

        binding.viewPagerFragments.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.tabLayout
            )
        )

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPagerFragments.currentItem = tab?.position ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun listener(){
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
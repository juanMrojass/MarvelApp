package com.manuelmarvelapp.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.manuelmarvelapp.ui.main.view.comics.ComicsFragment
import com.manuelmarvelapp.ui.main.view.series.SeriesFragment
import com.manuelmarvelapp.ui.main.view.stories.StoriesFragment

class DetailViewPagerAdapter(fm: FragmentManager, private val id: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ComicsFragment.newInstance(idCharacter = id)
            1 -> SeriesFragment.newInstance(idCharacter = id)
            2 -> StoriesFragment.newInstance(idCharacter = id)
            else -> ComicsFragment()
        }
    }
}
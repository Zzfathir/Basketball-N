package com.fathirdev.basketball_n.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fathirdev.basketball_n.BasketballFragment
import com.fathirdev.basketball_n.NbaFragment
import com.fathirdev.basketball_n.PlayersFragment

class SectionPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BasketballFragment()
            1 -> PlayersFragment()
            else -> NbaFragment()
        }
    }
}
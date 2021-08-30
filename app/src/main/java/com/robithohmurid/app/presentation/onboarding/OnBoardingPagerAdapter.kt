package com.robithohmurid.app.presentation.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnBoarding1Fragment()
            1 -> OnBoarding2Fragment()
            2 -> OnBoarding3Fragment()
            3 -> OnBoarding4Fragment()
            4 -> OnBoarding5Fragment()
            else -> OnBoarding1Fragment()
        }
    }
}
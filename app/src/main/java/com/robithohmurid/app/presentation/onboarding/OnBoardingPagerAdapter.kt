package com.robithohmurid.app.presentation.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.robithohmurid.app.R

class OnBoardingPagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    private val listFragment = listOf(
        BaseOnBoardingFragment.newInstance(
            fragment.getString(R.string.title_onboarding1),
            fragment.getString(R.string.desc_onboarding1),
            R.drawable.iv_ob1
        ),
        BaseOnBoardingFragment.newInstance(
            fragment.getString(R.string.title_onboarding2),
            fragment.getString(R.string.desc_onboarding2),
            R.drawable.iv_ob2
        ),
        BaseOnBoardingFragment.newInstance(
            fragment.getString(R.string.title_onboarding3),
            fragment.getString(R.string.desc_onboarding3),
            R.drawable.iv_ob3
        ),
//        BaseOnBoardingFragment.newInstance(
//            fragment.getString(R.string.title_onboarding4),
//            fragment.getString(R.string.desc_onboarding4),
//            R.drawable.iv_ob4
//        ),
        BaseOnBoardingFragment.newInstance(
            fragment.getString(R.string.title_onboarding5),
            fragment.getString(R.string.desc_onboarding5),
            R.drawable.iv_ob5
        )
    )

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]

}
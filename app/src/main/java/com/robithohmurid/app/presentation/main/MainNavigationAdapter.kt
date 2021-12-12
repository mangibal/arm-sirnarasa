package com.robithohmurid.app.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.robithohmurid.app.presentation.main.home.HomeFragment
import com.robithohmurid.app.presentation.main.info.InfoFragment
import com.robithohmurid.app.presentation.main.kalender.CalendarFragment

/**
 * Created by Iqbal Fauzi at 09/12/21
 * iqbal.fauzi.if99@gmail.com
 */
class MainNavigationAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragments = listOf(HomeFragment(), CalendarFragment(), InfoFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
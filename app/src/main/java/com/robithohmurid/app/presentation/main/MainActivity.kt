package com.robithohmurid.app.presentation.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.ActivityMainBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.extension.app.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate,
    MainViewModel::class
) {

    private var mDoubleTapExit = false

    private val navigationAdapter: MainNavigationAdapter by lazy {
        return@lazy MainNavigationAdapter(this)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            with(binding) {
                when (item.itemId) {
                    R.id.nav_home -> {
                        vpContent.setCurrentItem(0, false)
                    }
                    R.id.nav_calendar -> {
                        vpContent.setCurrentItem(1, false)
                    }
                    R.id.nav_info -> {
                        vpContent.setCurrentItem(2, false)
                    }
                }
            }
            true
        }

    override fun onBackPressed() {
        if (mDoubleTapExit) {
            super.onBackPressed()
            return
        }

        mDoubleTapExit = true
        showToast(getString(R.string.msg_press_again_to_exit))

        // return to normal state
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main) {
                mDoubleTapExit = false
            }
        }
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        with(binding) {
            vpContent.apply {
                offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
                isUserInputEnabled = false
                adapter = navigationAdapter
            }
            navView.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }

    override fun onInitData() {

    }

}
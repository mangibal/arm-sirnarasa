package com.robithohmurid.app.presentation.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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
        setupNavigation()
//        setupBottomNavigation()
    }

    private fun setupNavigation() {
        with(binding) {
            val navigationController = findNavController(R.id.nav_host_fragment)
            navView.setupWithNavController(navigationController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onInitData() {

    }

}
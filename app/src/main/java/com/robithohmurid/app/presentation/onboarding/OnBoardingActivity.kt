package com.robithohmurid.app.presentation.onboarding

import android.os.Build
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.ActivityOnBoardingBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.domain.router.ScreenRouter
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.loadImage
import com.robithohmurid.app.external.extension.view.makeStatusBarTransparent
import com.robithohmurid.app.external.extension.view.onClick
import org.koin.android.ext.android.inject

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding, OnBoardingViewModel>(
    ActivityOnBoardingBinding::inflate,
    OnBoardingViewModel::class
) {
    private val screenRouter: ScreenRouter by inject()
    override fun onInitUI(savedInstanceState: Bundle?) {
        setupStatusBar()
        with(binding) {
            if (Build.VERSION.SDK_INT >= 28) {
                ivBg.setImageDrawable(getDrawableCompat(R.drawable.bg_screen))
            }
            vpOnboarding.adapter = OnBoardingPagerAdapter(this@OnBoardingActivity)
            dotsIndicator.setViewPager2(vpOnboarding)
            vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        4 -> {
                            btnNext.apply {
                                text = getString(R.string.action_done)
                                icon = null
                                onClick {
                                    viewModel.sessionHelper.disableOnBoarding()
                                    screenRouter.gotoHomePage(this@OnBoardingActivity)
                                }
                            }
                        }
                        else -> {
                            btnNext.apply {
                                text = getString(R.string.action_next)
                                icon = getDrawableCompat(R.drawable.ic_next)
                                onClick {
                                    vpOnboarding.currentItem = position + 1
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onInitData() {

    }

    private fun setupStatusBar() {
        with(binding) {
            makeStatusBarTransparent()
            ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}
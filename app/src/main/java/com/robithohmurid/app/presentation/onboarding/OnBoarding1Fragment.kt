package com.robithohmurid.app.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.FragmentOnBoarding1Binding
import com.robithohmurid.app.domain.abstraction.BaseFragment

class OnBoarding1Fragment : BaseFragment<FragmentOnBoarding1Binding, OnBoardingViewModel>(
    FragmentOnBoarding1Binding::inflate,
    OnBoardingViewModel::class
) {
    override fun onInitUI(savedInstanceState: Bundle?) {

    }

    override fun onInitData() {

    }


}
package com.robithohmurid.app.presentation.onboarding

import android.os.Bundle
import androidx.annotation.DrawableRes
import coil.load
import com.robithohmurid.app.databinding.FragmentBaseOnBoardingBinding
import com.robithohmurid.app.domain.abstraction.BaseFragment

class BaseOnBoardingFragment : BaseFragment<FragmentBaseOnBoardingBinding, OnBoardingViewModel>(
    FragmentBaseOnBoardingBinding::inflate,
    OnBoardingViewModel::class
) {

    private var title: String? = null
    private var description: String? = null
    private var image: Int? = null


    companion object {
        @JvmStatic
        fun newInstance(title: String, description: String, @DrawableRes image: Int) =
            BaseOnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                    putString(DESCRIPTION, description)
                    putInt(IMAGE, image)
                }
            }
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        dataReceived?.let {
            title = it.getString(TITLE)
            description = it.getString(DESCRIPTION)
            image = it.getInt(IMAGE)
            with(binding){
                tvTitleOnboarding.text = title
                tvDescOnboarding.text = description
                imgOnboarding.load(image!!)
            }
        }
    }

    override fun onInitData() {

    }

}
const val TITLE = "title"
const val DESCRIPTION = "subtitle"
const val IMAGE = "image"

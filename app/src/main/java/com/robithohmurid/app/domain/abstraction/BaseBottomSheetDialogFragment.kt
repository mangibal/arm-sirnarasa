@file:Suppress("UNCHECKED_CAST")

package com.robithohmurid.app.domain.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.robithohmurid.app.R
import com.robithohmurid.app.domain.router.ScreenRouter
import com.robithohmurid.app.external.service.PrayHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Iqbal Fauzi on 07/06/21 21.48
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseBottomSheetDialogFragment<VB : ViewBinding, out VM : BaseViewModel>(
    private val viewBinder: (LayoutInflater) -> ViewBinding,
    kClass: KClass<VM>
) : BottomSheetDialogFragment() {

    protected val binding by lazy(LazyThreadSafetyMode.NONE) { viewBinder.invoke(layoutInflater) as VB }
    protected val prayerHelper: PrayHelper by inject()
    protected val viewModel: VM by viewModel(clazz = kClass)
    protected val router: ScreenRouter by inject()

    protected var dataReceived: Bundle? = null
    protected val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataReceived = arguments
        setDarkerBackground()
        onInitUI(savedInstanceState)
        onInitData()
    }

    private fun setDarkerBackground() {
        dialog?.window?.apply {
            attributes?.dimAmount = 0.7f
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()

}
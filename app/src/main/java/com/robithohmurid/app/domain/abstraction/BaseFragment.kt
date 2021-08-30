@file:Suppress("UNCHECKED_CAST")

package com.robithohmurid.app.domain.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import com.robithohmurid.app.domain.router.ScreenRouter
import com.robithohmurid.app.external.service.PrayHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Iqbal Fauzi on 07/06/21 21.48
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseFragment<VB : ViewBinding, out VM : BaseViewModel>(
    private val viewBinder: (LayoutInflater) -> ViewBinding,
    kClass: KClass<VM>
) : Fragment() {

    protected val binding by lazy(LazyThreadSafetyMode.NONE) { viewBinder.invoke(layoutInflater) as VB }
    protected val prayerHelper: PrayHelper by inject()
    protected val viewModel: VM by viewModel(clazz = kClass)
    protected val router: ScreenRouter by inject()

    protected var dataReceived: Bundle? = null
    protected val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataReceived = arguments
        onInitUI(savedInstanceState)
        onInitData()
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()

}
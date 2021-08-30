@file:Suppress("UNCHECKED_CAST")

package com.robithohmurid.app.domain.abstraction

import android.content.*
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewbinding.ViewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.internal.ContextUtils
import com.google.gson.Gson
import com.robithohmurid.app.BuildConfig
import com.robithohmurid.app.domain.router.ScreenRouter
import com.robithohmurid.app.external.extension.app.LanguageUtils
import com.robithohmurid.app.external.extension.app.localeId
import com.robithohmurid.app.external.extension.app.showToast
import com.robithohmurid.app.external.firebase.CloudMessaging
import com.robithohmurid.app.external.firebase.Crashlytics
import com.robithohmurid.app.external.service.PrayHelper
import com.robithohmurid.app.external.service.PrayTime
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.reflect.KClass

/**
 * Created by Iqbal Fauzi on 03/06/21 18.46
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseActivity<VB : ViewBinding, out VM : BaseViewModel>(
    private val viewBinder: (LayoutInflater) -> ViewBinding,
    kClass: KClass<VM>
) : AppCompatActivity() {

    companion object {
        const val ROUTER_MESSAGE = "${BuildConfig.APPLICATION_ID}.router.message"
        const val MESSAGE = "routerMessage"
    }

    private val chainRouterMessage = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ROUTER_MESSAGE) {
                val message = intent.getStringExtra(MESSAGE)
                showToast(message.toString())
            }
        }
    }

    protected val binding by lazy(LazyThreadSafetyMode.NONE) { viewBinder.invoke(layoutInflater) as VB }
    protected val prayerHelper: PrayHelper by inject()
    protected val prayTime: PrayTime by inject()
    protected val viewModel: VM by viewModel(clazz = kClass)
    protected val crashlytics: Crashlytics by inject()
    protected val messaging: CloudMessaging by inject()
    protected val router: ScreenRouter by inject()

    protected lateinit var mFusedLocationClient: FusedLocationProviderClient

    protected var dataReceived: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onInitUI(savedInstanceState)
        onInitData()
    }

    abstract fun onInitUI(savedInstanceState: Bundle?)

    abstract fun onInitData()

    override fun attachBaseContext(newBase: Context) {
        val localeUpdatedContext: ContextWrapper = LanguageUtils.updateLocale(newBase, localeId)
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onResume() {
        super.onResume()
        val routerMessage = IntentFilter(ROUTER_MESSAGE)
        LocalBroadcastManager.getInstance(this).registerReceiver(chainRouterMessage, routerMessage)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(chainRouterMessage)
    }

}
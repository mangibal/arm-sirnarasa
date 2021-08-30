package com.robithohmurid.app.external.extension.app

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * Created by Iqbal Fauzi on 21/08/21 11.12
 * iqbal.fauzi.if99@gmail.com
 */
class LanguageUtils(base: Context) : ContextWrapper(base) {

    companion object {
        fun updateLocale(ctx: Context, locale: Locale): ContextWrapper {
            val resources = ctx.resources
            val configuration = resources.configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.setLocale(locale)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                ctx.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return LanguageUtils(ctx)
        }
    }

}
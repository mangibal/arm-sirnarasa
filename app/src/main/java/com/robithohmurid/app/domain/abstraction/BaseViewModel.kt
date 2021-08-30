package com.robithohmurid.app.domain.abstraction

import androidx.lifecycle.ViewModel
import com.robithohmurid.app.data.local.SessionHelper

/**
 * Created by Iqbal Fauzi on 03/06/21 18.47
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseViewModel: ViewModel() {
    val sessionHelper = SessionHelper()
}
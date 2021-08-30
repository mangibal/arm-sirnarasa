package com.robithohmurid.app.data.model.base

import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbal Fauzi on 3/19/21 1:59 PM
 * iqbal.fauzi.if99@gmail.com
 */
open class BaseResponse {
    @SerializedName("status") var status: Int? = 0
    @SerializedName("message") var message: String? = ""
}
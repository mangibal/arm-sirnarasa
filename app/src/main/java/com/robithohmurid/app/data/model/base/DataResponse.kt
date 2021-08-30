package com.robithohmurid.app.data.model.base

import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbal Fauzi on 11/06/21 21.38
 * iqbal.fauzi.if99@gmail.com
 */
data class DataResponse<T>(
    @SerializedName("data") val data: T?
): BaseResponse()
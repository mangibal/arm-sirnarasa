package com.robithohmurid.app.data.local

import com.orhanobut.hawk.Hawk
import com.robithohmurid.app.data.local.sholat.LocationData
import com.robithohmurid.app.external.constant.SessionConstant

/**
 * Created by Iqbal Fauzi on 07/06/21 21.36
 * iqbal.fauzi.if99@gmail.com
 */
class SessionHelper {

    fun setLocationType(type: Int) = Hawk.put(SessionConstant.LOCATION_TYPE, type)

    fun getLocationType(): Int = Hawk.get(SessionConstant.LOCATION_TYPE, 0)

    fun setLocation(locationData: LocationData) =
        Hawk.put(SessionConstant.LOCATION_DATA, locationData)

    fun getLocation() = Hawk.get(SessionConstant.LOCATION_DATA, LocationData())

    fun disableOnBoarding() = Hawk.put(SessionConstant.IS_ONBOARDING, false)

    fun isOnboarding(): Boolean = Hawk.get(SessionConstant.IS_ONBOARDING, true)

    fun addSession(key: String, value: Int) = Hawk.put(key, value)

    fun getSession(key: String): Int = Hawk.get(key, 0)

    fun deleteSession(key: String) = Hawk.delete(key)

    fun clearSession() = Hawk.deleteAll()

}
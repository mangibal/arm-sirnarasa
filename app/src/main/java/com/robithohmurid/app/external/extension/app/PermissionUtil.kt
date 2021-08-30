package com.robithohmurid.app.external.extension.app

import android.app.Activity
import android.app.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import com.robithohmurid.app.R

/**
 * Created by Iqbal Fauzi on 11/06/21 23.26
 * iqbal.fauzi.if99@gmail.com
 */
abstract class PermissionUtil(activity: Activity, permission: String?, permissionName: String?) {

    abstract fun onGranted(response: PermissionGrantedResponse?)
    abstract fun onDenied(response: PermissionDeniedResponse?)

    init {
        Dexter.withContext(activity)
            .withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    onGranted(response)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    onDenied(response)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: com.karumi.dexter.listener.PermissionRequest,
                    token: PermissionToken
                ) {
                    val message = String.format(
                        activity.getString(R.string.msg_permission),
                        permissionName
                    )
                    AlertDialog.Builder(activity)
                        .setTitle(R.string.label_permission)
                        .setMessage(message)
                        .setNegativeButton(
                            R.string.action_cancel
                        ) { dialogInterface, i ->
                            dialogInterface.dismiss()
                            token.cancelPermissionRequest()
                        }
                        .setPositiveButton(R.string.action_ok) { dialogInterface, _ ->
                            dialogInterface.dismiss()
                            token.continuePermissionRequest()
                        }
                        .setOnDismissListener { token.cancelPermissionRequest() }
                        .show()
                }
            })
            .withErrorListener { error ->
                activity.showToast(error.toString())
            }
            .check()
    }
}
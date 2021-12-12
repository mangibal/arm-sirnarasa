package com.robithohmurid.app.presentation.dialog

import android.content.Context
import android.os.Bundle
import com.robithohmurid.app.data.local.sholat.LocationData
import com.robithohmurid.app.databinding.DialogLocationBinding
import com.robithohmurid.app.domain.abstraction.BaseDialogFragment
import com.robithohmurid.app.external.extension.view.onClick
import com.robithohmurid.app.presentation.main.MainViewModel

/**
 * Created by Iqbal Fauzi 18/08/2021
 * iqbal.fauzi.if99@gmail.com
 */
class LocationDialogFragment : BaseDialogFragment<DialogLocationBinding, MainViewModel>(
    DialogLocationBinding::inflate,
    MainViewModel::class
) {

    private var listener: OnLocationSelectedListener? = null

    interface OnLocationSelectedListener {
        fun onLocationUpdated()
        fun onSirnarasaSelected(locationData: LocationData)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as OnLocationSelectedListener
        } catch (e: ClassCastException) {
            throw RuntimeException("$context must implement OnLocationSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            btnUpdate.onClick {
                listener?.onLocationUpdated()
                dismiss()
            }
            btnSirnasa.onClick {
                listener?.onSirnarasaSelected(LocationData())
                dismiss()
            }
        }
    }

    override fun onInitData() {

    }

}
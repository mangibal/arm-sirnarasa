package com.robithohmurid.app.presentation.splash

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.DialogUpdateBinding
import com.robithohmurid.app.domain.abstraction.BaseDialogFragment
import com.robithohmurid.app.domain.abstraction.BaseViewModel
import com.robithohmurid.app.external.constant.IntentKey
import com.robithohmurid.app.external.extension.view.onClick

/**
 * Created by Iqbal Fauzi on 3/25/21 1:42 PM
 * iqbal.fauzi.if99@gmail.com
 */
@Suppress("unused")
class UpdateDialog : BaseDialogFragment<DialogUpdateBinding, BaseViewModel>(
    DialogUpdateBinding::inflate,
    BaseViewModel::class
) {

    private val isForceUpdate: Boolean by lazy {
        return@lazy dataReceived?.getBoolean(IntentKey.UPDATE_KEY, false) ?: false
    }

    private var listener: UpdateListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as UpdateListener
        } catch (e: ClassCastException) {
            throw RuntimeException("$context must implement UpdateListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.run {
            setCancelable(!isForceUpdate)
            setCanceledOnTouchOutside(!isForceUpdate)
        }
        isCancelable = !isForceUpdate
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            btnLater.apply {
//                setEnableView(isForceUpdate)
                onClick {
                    listener?.onLaterListener()
                    dismiss()
                }
            }
            btnUpdate.onClick {
                listener?.onUpdateListener()
            }
        }
    }

    override fun onInitData() {

    }

}
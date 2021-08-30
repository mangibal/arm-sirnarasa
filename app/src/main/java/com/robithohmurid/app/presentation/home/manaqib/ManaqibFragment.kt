package com.robithohmurid.app.presentation.home.manaqib

import android.os.Bundle
import com.robithohmurid.app.data.local.listMenuManaqib
import com.robithohmurid.app.databinding.FragmentManaqibBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.external.extension.view.setupGridList
import com.robithohmurid.app.external.extension.view.snackBar
import com.robithohmurid.app.presentation.home.HomeViewModel
import com.robithohmurid.app.presentation.home.adapter.MenuGridAdapter

/**
 * Created by Iqbal Fauzi on 19/06/21 19.53
 * iqbal.fauzi.if99@gmail.com
 */
class ManaqibFragment : BaseBottomSheetDialogFragment<FragmentManaqibBinding, HomeViewModel>(
    FragmentManaqibBinding::inflate,
    HomeViewModel::class
) {

    private val menuAdapter: MenuGridAdapter by lazy { MenuGridAdapter() }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            requireContext().setupGridList(rvManaqib, 4)
            rvManaqib.adapter = menuAdapter

            menuAdapter.run {
                setItems(listMenuManaqib)
                setListener {
                    root.snackBar(it.name)
                }
            }
        }
    }

    override fun onInitData() {

    }
}
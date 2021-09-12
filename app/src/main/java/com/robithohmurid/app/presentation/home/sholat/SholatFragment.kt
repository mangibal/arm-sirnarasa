package com.robithohmurid.app.presentation.home.sholat

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.listMenuManaqib
import com.robithohmurid.app.data.local.listSholat
import com.robithohmurid.app.databinding.FragmentManaqibBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.domain.router.ActivityScreen
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.view.setupGridList
import com.robithohmurid.app.external.extension.view.snackBar
import com.robithohmurid.app.presentation.home.HomeViewModel
import com.robithohmurid.app.presentation.home.adapter.MenuGridAdapter

/**
 * Created by Iqbal Fauzi on 19/06/21 19.53
 * iqbal.fauzi.if99@gmail.com
 */
class SholatFragment : BaseBottomSheetDialogFragment<FragmentManaqibBinding, HomeViewModel>(
    FragmentManaqibBinding::inflate,
    HomeViewModel::class
) {

    private val menuAdapter: MenuGridAdapter by lazy { MenuGridAdapter() }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            requireContext().setupGridList(rvManaqib, 4)
            tvAmaliyahTitle.text = getString(R.string.title_sholat)
            rvManaqib.adapter = menuAdapter

            menuAdapter.run {
                setItems(listSholat)
                setListener {
                    router.gotoListContent(
                        requireActivity(),
                        category = CategoryConstant.AMALIYAH_KEY,
                        alias = it.alias,
                        title = it.name,
                    )
                }
            }
        }
    }

    override fun onInitData() {

    }
}
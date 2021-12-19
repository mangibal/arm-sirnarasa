package com.robithohmurid.app.presentation.main.sholat

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.listSholat
import com.robithohmurid.app.databinding.FragmentManaqibBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.extension.view.setupGridList
import com.robithohmurid.app.presentation.main.MainViewModel
import com.robithohmurid.app.presentation.main.adapter.MenuGridAdapter

/**
 * Created by Iqbal Fauzi on 19/06/21 19.53
 * iqbal.fauzi.if99@gmail.com
 */
class SholatFragment : BaseBottomSheetDialogFragment<FragmentManaqibBinding, MainViewModel>(
    FragmentManaqibBinding::inflate,
    MainViewModel::class
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
                    router.navigateToListContent(
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
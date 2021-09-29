package com.robithohmurid.app.presentation.home.manaqib

import android.os.Bundle
import com.robithohmurid.app.data.local.amaliyah.listSholawat
import com.robithohmurid.app.data.local.amaliyah.tanbihData
import com.robithohmurid.app.data.local.amaliyah.tawasulData
import com.robithohmurid.app.data.local.listMenuManaqib
import com.robithohmurid.app.databinding.FragmentManaqibBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.view.setupGridList
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
                    showMenu(it.alias, it.name)
                }
            }
        }
    }

    private fun showMenu(alias: String, title: String) {
        when (alias) {
            MenuConstant.MC_MANAQIB -> {
                router.gotoContent(
                    requireActivity(),
                    category = CategoryConstant.AMALIYAH_KEY,
                    contentAlias = alias,
                    item = alias,
                    title = title
                )
            }
            MenuConstant.SHOLAWAT_THORIQIYYAH -> {
                router.gotoContent2(requireActivity(), listSholawat[4].title, listSholawat[4].content)
            }
            MenuConstant.MANQOBAH -> {
                router.gotoListContent(
                    requireActivity(),
                    CategoryConstant.AMALIYAH_KEY,
                    alias,
                    title
                )
            }
            MenuConstant.TAWASSUL -> {
                router.gotoContent2(requireActivity(), title, tawasulData.content)
            }
            MenuConstant.TANBIH -> {
                router.gotoContent2(requireActivity(), title, tanbihData.content)
            }
        }

    }

    override fun onInitData() {

    }
}
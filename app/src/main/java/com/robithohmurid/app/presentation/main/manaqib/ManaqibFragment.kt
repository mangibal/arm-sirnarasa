package com.robithohmurid.app.presentation.main.manaqib

import android.os.Bundle
import com.robithohmurid.app.data.local.amaliyah.listSholawat
import com.robithohmurid.app.data.local.amaliyah.manaqib.mcManaqibData
import com.robithohmurid.app.data.local.amaliyah.tanbihData
import com.robithohmurid.app.data.local.amaliyah.tawasulData
import com.robithohmurid.app.data.local.listMenuManaqib
import com.robithohmurid.app.databinding.FragmentManaqibBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.view.setupGridList
import com.robithohmurid.app.presentation.main.MainViewModel
import com.robithohmurid.app.presentation.main.adapter.MenuGridAdapter

/**
 * Created by Iqbal Fauzi on 19/06/21 19.53
 * iqbal.fauzi.if99@gmail.com
 */
class ManaqibFragment : BaseBottomSheetDialogFragment<FragmentManaqibBinding, MainViewModel>(
    FragmentManaqibBinding::inflate,
    MainViewModel::class
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
                router.navigateToContent(requireActivity(), title, mcManaqibData.content)
            }
            MenuConstant.SHOLAWAT_THORIQIYYAH -> {
                router.navigateToContent(requireActivity(), listSholawat[3].title, listSholawat[3].content)
            }
            MenuConstant.MANQOBAH -> {
                router.navigateToListContent(
                    requireActivity(),
                    CategoryConstant.AMALIYAH_KEY,
                    alias,
                    title
                )
            }
            MenuConstant.TAWASSUL -> {
                router.navigateToContent(requireActivity(), title, tawasulData.content)
            }
            MenuConstant.TANBIH -> {
                router.navigateToContent(requireActivity(), title, tanbihData.content)
            }
        }

    }

    override fun onInitData() {

    }
}
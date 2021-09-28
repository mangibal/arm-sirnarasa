package com.robithohmurid.app.presentation.home.menu

import android.os.Bundle
import com.robithohmurid.app.data.local.amaliyah.dzikirData
import com.robithohmurid.app.data.local.amaliyah.khotamanData
import com.robithohmurid.app.data.local.amaliyah.tawasulData
import com.robithohmurid.app.data.local.listAmaliyah
import com.robithohmurid.app.data.local.listMenuTqn
import com.robithohmurid.app.databinding.FragmentLainnyaBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.view.setupList
import com.robithohmurid.app.presentation.home.HomeViewModel
import com.robithohmurid.app.presentation.home.manaqib.ManaqibFragment
import com.robithohmurid.app.presentation.home.sholat.SholatFragment

/**
 * Created by Iqbal Fauzi on 19/06/21 19.53
 * iqbal.fauzi.if99@gmail.com
 */
class MenuFragment : BaseBottomSheetDialogFragment<FragmentLainnyaBinding, HomeViewModel>(
    FragmentLainnyaBinding::inflate,
    HomeViewModel::class
) {

    private val listAmaliyahAdapter: ListMenuAdapter by lazy { ListMenuAdapter() }
    private val listTqnAdapter: ListMenuAdapter by lazy { ListMenuAdapter() }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupAmaliyahList()
        setupTqnList()
    }

    private fun setupTqnList() {
        with(binding) {
            requireContext().setupList(rvTqn)
            rvTqn.adapter = listTqnAdapter

            listTqnAdapter.run {
                setItems(listMenuTqn)
                setListener {
                    showMenu(it.alias, it.name)
                }
            }
        }
    }

    private fun setupAmaliyahList() {
        with(binding) {
            requireContext().setupList(rvAmaliyah)
            rvAmaliyah.adapter = listAmaliyahAdapter

            listAmaliyahAdapter.run {
                setItems(listAmaliyah)
                setListener {
                    showMenu(it.alias, it.name)
                }
            }
        }
    }

    private fun showMenu(alias: String, title: String) {
        when (alias) {
            MenuConstant.ADAB -> router.gotoListContent(
                requireActivity(),
                CategoryConstant.AMALIYAH_KEY,
                alias,
                title
            )
            MenuConstant.SHOLAT -> SholatFragment().run {
                show(this@MenuFragment.childFragmentManager, SholatFragment().tag)
            }
            MenuConstant.DZIKIR -> {
                router.gotoContent2(requireActivity(), title, dzikirData.content)
            }
            MenuConstant.TAWASSUL -> {
                router.gotoContent2(requireActivity(), title, tawasulData.content)
            }
            MenuConstant.KHOTAMAN, MenuConstant.TANBIH -> {
                router.gotoContent2(requireActivity(),title, khotamanData.content)
            }
            MenuConstant.MANAQIB -> ManaqibFragment().run {
                show(this@MenuFragment.childFragmentManager, ManaqibFragment().tag)
            }
            MenuConstant.SHOLAWAT, MenuConstant.DOA -> {
                router.gotoListContent(
                    requireActivity(),
                    CategoryConstant.TQN_KEY,
                    alias,
                    title
                )
            }
            MenuConstant.TUJUAN_DASAR, MenuConstant.DIAGRAM_LATIFAH, MenuConstant.ZIARAH,
            MenuConstant.SILSILAH, MenuConstant.SYEKH, MenuConstant.TARHIM -> {
                router.gotoContent(
                    requireActivity(),
                    CategoryConstant.TQN_KEY,
                    alias,
                    alias,
                    title
                )
            }
        }

    }

    override fun onInitData() {

    }
}
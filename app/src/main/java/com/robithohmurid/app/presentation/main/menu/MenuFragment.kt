package com.robithohmurid.app.presentation.main.menu

import android.os.Bundle
import com.robithohmurid.app.data.local.amaliyah.*
import com.robithohmurid.app.data.local.listMenuTqn
import com.robithohmurid.app.data.local.tqn.namaSyekhAbdulQodirData
import com.robithohmurid.app.data.local.tqn.silsilahTqnData
import com.robithohmurid.app.data.local.tqn.tujuanDasarData
import com.robithohmurid.app.data.local.tqn.tujuhLatifahData
import com.robithohmurid.app.databinding.FragmentLainnyaBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.view.setupList
import com.robithohmurid.app.presentation.main.MainViewModel
import com.robithohmurid.app.presentation.main.manaqib.ManaqibFragment
import com.robithohmurid.app.presentation.main.sholat.SholatFragment

/**
 * Created by Iqbal Fauzi on 19/06/21 19.53
 * iqbal.fauzi.if99@gmail.com
 */
class MenuFragment : BaseBottomSheetDialogFragment<FragmentLainnyaBinding, MainViewModel>(
    FragmentLainnyaBinding::inflate,
    MainViewModel::class
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
                setItems(com.robithohmurid.app.data.local.listAmaliyah)
                setListener {
                    showMenu(it.alias, it.name)
                }
            }
        }
    }

    private fun showMenu(alias: String, title: String) {
        when (alias) {
            MenuConstant.ADAB -> router.navigateToListContent(
                requireActivity(),
                CategoryConstant.AMALIYAH_KEY,
                alias,
                title
            )
            MenuConstant.SHOLAT -> SholatFragment().run {
                show(this@MenuFragment.childFragmentManager, SholatFragment().tag)
            }
            MenuConstant.DZIKIR -> {
                router.navigateToContent(requireActivity(), title, dzikirData.content)
            }
            MenuConstant.TAWASSUL -> {
                router.navigateToContent(requireActivity(), title, tawasulData.content)
            }
            MenuConstant.TANBIH -> {
                router.navigateToContent(requireActivity(), title, tanbihData.content)
            }
            MenuConstant.KHOTAMAN -> {
                router.navigateToContent(requireActivity(), title, khotamanData.content)
            }
            MenuConstant.MANAQIB -> ManaqibFragment().run {
                show(this@MenuFragment.childFragmentManager, ManaqibFragment().tag)
            }
            MenuConstant.DOA -> {
                router.navigateToListContent(
                    requireActivity(),
                    CategoryConstant.TQN_KEY,
                    alias,
                    title
                )
            }
            MenuConstant.SHOLAWAT -> {
                router.navigateToListContent(
                    requireActivity(),
                    CategoryConstant.TQN_KEY,
                    alias,
                    title
                )
            }
            MenuConstant.ZIARAH -> {
                router.navigateToContent(requireActivity(), title, tahlilData.content)
            }
            MenuConstant.TUJUAN_DASAR -> {
                router.navigateToContent(
                    requireActivity(),
                    tujuanDasarData.title,
                    tujuanDasarData.content
                )
            }
            MenuConstant.DIAGRAM_LATIFAH -> {
                router.navigateToContent(requireActivity(), title, tujuhLatifahData.content)
            }
            MenuConstant.SILSILAH -> {
                router.navigateToContent(requireActivity(), title, silsilahTqnData.content)
            }
            MenuConstant.SYEKH -> {
                router.navigateToContent(requireActivity(), title, namaSyekhAbdulQodirData.content)
            }
            MenuConstant.TARHIM -> {
                router.navigateToContent(requireActivity(), title, tarhimData.content)
            }
        }

    }

    override fun onInitData() {

    }
}
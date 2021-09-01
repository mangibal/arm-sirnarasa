package com.robithohmurid.app.presentation.home.menu

import android.os.Bundle
import com.robithohmurid.app.data.local.listAmaliyah
import com.robithohmurid.app.data.local.listMenuTqn
import com.robithohmurid.app.databinding.FragmentLainnyaBinding
import com.robithohmurid.app.domain.abstraction.BaseBottomSheetDialogFragment
import com.robithohmurid.app.domain.router.ActivityScreen
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.app.showToast
import com.robithohmurid.app.external.extension.view.setupList
import com.robithohmurid.app.presentation.home.HomeViewModel
import com.robithohmurid.app.presentation.home.manaqib.ManaqibFragment

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
                    val intent = router.getIntentScreen(requireContext(), ActivityScreen.Content).apply {
                        putExtra("id",it.id)
                        putExtra("title",it.name)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    private fun setupAmaliyahList() {
        with(binding) {
            requireContext().setupList(rvAmaliyah)
            rvAmaliyah.adapter = listAmaliyahAdapter

            listAmaliyahAdapter.run {
                val listAmaliyah = listAmaliyah.filterNot {
                    it.name == MenuConstant.LAINNYA
                    it.name == MenuConstant.MANAQIB
                }
                setItems(listAmaliyah)
                setListener {
                    val intent = router.getIntentScreen(requireContext(), ActivityScreen.ListContent).apply {
                        putExtra("id",it.id)
                        putExtra("title",it.name)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    override fun onInitData() {

    }
}
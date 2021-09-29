package com.robithohmurid.app.presentation.listcontent

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.amaliyah.listSholatHarian
import com.robithohmurid.app.data.local.amaliyah.listSholatTahunan
import com.robithohmurid.app.data.local.amaliyah.listSholawat
import com.robithohmurid.app.data.model.entity.ListingEntity
import com.robithohmurid.app.databinding.ActivityListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.constant.IntentKey
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.custom.AppBar
import com.robithohmurid.app.external.extension.view.getString
import com.robithohmurid.app.external.extension.view.initToolbar
import com.robithohmurid.app.external.extension.view.setupList
import com.robithohmurid.app.external.extension.view.showIf

class ListContentActivity : BaseActivity<ActivityListContentBinding, ListContentViewModel>(
    ActivityListContentBinding::inflate,
    ListContentViewModel::class
), AppBar.ToolbarListener {

    private val listData: List<ListingEntity> = emptyList()

    private val listContentAdapter by lazy {
        return@lazy ListContentAdapter()
    }

    private val category: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.CATEGORY_KEY) ?: ""
    }

    private val contentAlias: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.CONTENT_KEY) ?: ""
    }

    private val title: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.TITLE_KEY) ?: ""
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        initToolbar(binding.appBar.appToolbar, title)
        setupHeader()
        setupListContent()
    }

    private fun setupHeader() {
        with(binding) {
            appBar.run {
                setToolbarTitle(title)
                setToolbarListener(this@ListContentActivity)
                when (contentAlias) {
                    MenuConstant.ADAB -> setImageDrawable(R.drawable.iv_adab)
                    MenuConstant.SHOLAT, MenuConstant.SHOLAT_HARIAN, MenuConstant.SHOLAT_TAHUNAN -> setImageDrawable(
                        R.drawable.iv_sholat
                    )
                    MenuConstant.MANAQIB -> setImageDrawable(R.drawable.iv_profil_syekh)
                    MenuConstant.SHOLAWAT -> setImageDrawable(R.drawable.iv_sholawat)
                    else -> setImageDrawable(R.drawable.iv_jadwal_sholat)
                }
            }

        }

    }

    private fun setupListContent() {
        with(binding) {
            listContentAdapter.setListener {
                router.gotoContent2(this@ListContentActivity, it.title, it.content)
//                router.gotoContent(
//                    this@ListContentActivity,
//                    category = category,
//                    contentAlias = contentAlias,
//                    item = it.alias,
//                    title = it.name
//                )
            }
            rvListContent.apply {
                setupList(this)
                adapter = listContentAdapter
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
//            observe(isLoading, ::onLoadingData)
//            observe(listContent, ::onListContent)
//
//            getListContent(category, contentAlias)
            getListContent(key = contentAlias)
        }
    }

    private fun getListContent(key: String) {
        when (key) {
            MenuConstant.ADAB -> {

            }
            MenuConstant.SHOLAT_HARIAN -> {
                onListContent(listSholatHarian)
            }
            MenuConstant.SHOLAT_TAHUNAN -> {
                onListContent(listSholatTahunan)
            }
            MenuConstant.SHOLAWAT -> {
                onListContent(listSholawat)
            }
            MenuConstant.DOA -> {

            }
            MenuConstant.MANQOBAH -> {

            }
        }
    }

    private fun onLoadingData(isLoading: Boolean) {
        binding.pbContent.showIf(isLoading)
    }

    private fun onListContent(list: List<ListingEntity>) {
        with(binding) {
            rvListContent.showIf(list.isNotEmpty())
            listContentAdapter.setItems(list)
            tvMemuat.run {
                showIf(list.isEmpty())
                if (list.isEmpty()) {
                    text = getString(R.string.msg_data_kosong)
                }
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        val searchMenu = menu?.findItem(R.id.app_bar_search)
//        val searchView = (searchMenu?.actionView as SearchView)
//        searchView.setOnQueryTextListener(object :
//            SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
////                listContentAdapter.filter.filter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
////                listContentAdapter.filter.filter(newText)
//                return false
//            }
//        })
//        return true
//    }


}
package com.robithohmurid.app.presentation.listcontent

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.databinding.ActivityListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.constant.IntentKey
import com.robithohmurid.app.external.constant.MenuConstant.ID_ADAB
import com.robithohmurid.app.external.constant.MenuConstant.ID_SHOLAT_HARIAN
import com.robithohmurid.app.external.constant.MenuConstant.ID_SHOLAWAT
import com.robithohmurid.app.external.constant.MenuConstant.ID_SILSILAH
import com.robithohmurid.app.external.constant.MenuConstant.ID_SYEKH_AQJ
import com.robithohmurid.app.external.custom.AppBar
import com.robithohmurid.app.external.extension.app.getAliasById
import com.robithohmurid.app.external.extension.app.observe
import com.robithohmurid.app.external.extension.view.initToolbar
import com.robithohmurid.app.external.extension.view.setupList

class ListContentActivity : BaseActivity<ActivityListContentBinding, ListContentViewModel>(
    ActivityListContentBinding::inflate,
    ListContentViewModel::class
), AppBar.ToolbarListener {

    private val listContentAdapter by lazy {
        return@lazy ListContentAdapter()
    }

    private val category: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.CATEGORY_KEY) ?: ""
    }

    private val contentId: Int by lazy {
        return@lazy dataReceived?.getInt(IntentKey.CONTENT_KEY) ?: 0
    }

    private val title: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.TITLE_KEY) ?: ""
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        initToolbar(binding.appBar.appToolbar, title)
        setupHeader()
        setupListContent()
    }

    private fun setupListContent() {
        with(binding) {
            listContentAdapter.setListener {
                router.gotoContent(
                    this@ListContentActivity,
                    category = category,
                    content = contentId.getAliasById(),
                    item = it.alias,
                    title = it.name
                )
            }
            rvListContent.apply {
                setupList(this)
                adapter = listContentAdapter
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(listContent, ::onListContent)

            getListContent(category, contentId.getAliasById())
        }
    }

    private fun onListContent(list: List<ContentEntity>) {
        listContentAdapter.setItems(list)
    }

    private fun setupHeader() {
        with(binding) {
            appBar.run {
                setToolbarTitle(title)
                setToolbarListener(this@ListContentActivity)
                when (contentId) {
                    ID_ADAB -> setImageDrawable(R.drawable.iv_adab)
                    ID_SHOLAT_HARIAN -> setImageDrawable(R.drawable.iv_sholat)
                    ID_SYEKH_AQJ -> setImageDrawable(R.drawable.iv_profil_syekh)
                    ID_SILSILAH -> setImageDrawable(R.drawable.iv_silsilah)
                    ID_SHOLAWAT -> setImageDrawable(R.drawable.iv_sholawat)
                    else -> setImageDrawable(R.drawable.iv_jadwal_sholat)
                }
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchMenu = menu?.findItem(R.id.app_bar_search)
        val searchView = (searchMenu?.actionView as SearchView)
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                listContentAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                listContentAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }


}
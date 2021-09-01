package com.robithohmurid.app.presentation.listcontent

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.annotation.DrawableRes
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.listContentAdab
import com.robithohmurid.app.databinding.ActivityListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.domain.router.ActivityScreen
import com.robithohmurid.app.external.constant.MenuConstant.ADAB
import com.robithohmurid.app.external.constant.MenuConstant.ID_ADAB
import com.robithohmurid.app.external.constant.MenuConstant.ID_SHOLAT
import com.robithohmurid.app.external.constant.MenuConstant.ID_SHOLAWAT
import com.robithohmurid.app.external.constant.MenuConstant.ID_SILSILAH
import com.robithohmurid.app.external.constant.MenuConstant.ID_SYEKH_AQJ
import com.robithohmurid.app.external.constant.MenuConstant.SHOLAT
import com.robithohmurid.app.external.custom.AppBar
import com.robithohmurid.app.external.extension.view.initToolbar
import com.robithohmurid.app.external.extension.view.setupList

class ListContentActivity : BaseActivity<ActivityListContentBinding, ListContentViewModel>(
    ActivityListContentBinding::inflate,
    ListContentViewModel::class
), AppBar.ToolbarListener {

    private val listContentAdapter by lazy {
        return@lazy ListContentAdapter()
    }


    override fun onInitUI(savedInstanceState: Bundle?) {
        val idListContent = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")
        title?.let {
            initToolbar(binding.appBar.appToolbar, title)
            setupHeader(it, idListContent)
            with(binding) {
                rvListContent.apply {
                    setupList(this)
                    adapter = listContentAdapter
                }
            }


        }
        listContentAdapter.setItems(listContentAdab)
        listContentAdapter.setListener {
            val intent = router.getIntentScreen(this, ActivityScreen.Content).apply {
                putExtra("title", it.title)
                putExtra("id", it.id)
            }
            startActivity(intent)
        }
    }

    override fun onInitData() {

    }

    private fun setupHeader(title: String, id: Int) {
        with(binding) {
            appBar.run {
                setToolbarTitle(title)
                setToolbarListener(this@ListContentActivity)
                id.let { _id ->
                    when (_id) {
                        ID_ADAB -> setImageDrawable(R.drawable.iv_adab)
                        ID_SHOLAT -> setImageDrawable(R.drawable.iv_sholat)
                        ID_SYEKH_AQJ -> setImageDrawable(R.drawable.iv_profil_syekh)
                        ID_SILSILAH -> setImageDrawable(R.drawable.iv_silsilah)
                        ID_SHOLAWAT -> setImageDrawable(R.drawable.iv_sholawat)
                        else -> setImageDrawable(R.drawable.iv_jadwal_sholat)

                    }
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
                listContentAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listContentAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }


}
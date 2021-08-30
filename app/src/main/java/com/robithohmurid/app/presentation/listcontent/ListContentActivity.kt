package com.robithohmurid.app.presentation.listcontent

import android.os.Bundle
import androidx.annotation.DrawableRes
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.listContentAdab
import com.robithohmurid.app.databinding.ActivityListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.domain.router.ActivityScreen
import com.robithohmurid.app.external.custom.AppBar
import com.robithohmurid.app.external.extension.view.setupList

class ListContentActivity : BaseActivity<ActivityListContentBinding, ListContentViewModel>(
    ActivityListContentBinding::inflate,
    ListContentViewModel::class
), AppBar.ToolbarListener {

    private val listContentAdapter by lazy {
        return@lazy ListContentAdapter()
    }


    override fun onInitUI(savedInstanceState: Bundle?) {
        val title = intent.getStringExtra("title")
        title?.let {
            setupHeader(it)
            with(binding) {
                rvListContent.apply {
                    setupList(this)
                    adapter = listContentAdapter
                }
            }

        }
        listContentAdapter.setItems(listContentAdab)
        listContentAdapter.setListener {
            val intent = router.getIntentScreen(this, ActivityScreen.Content)
            startActivity(intent)
        }
    }

    override fun onInitData() {

    }

    private fun setupHeader(title: String, @DrawableRes image: Int = R.drawable.iv_jadwal_sholat) {
        with(binding) {

            appBar.run {
                setToolbarListener(this@ListContentActivity)
                setImageDrawable(image)
                setToolbarTitle(title)
            }

        }

    }

}
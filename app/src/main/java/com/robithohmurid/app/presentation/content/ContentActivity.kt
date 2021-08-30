package com.robithohmurid.app.presentation.content

import android.os.Bundle
import com.robithohmurid.app.data.local.sampleContentMarkwon
import com.robithohmurid.app.data.model.ContentEntity
import com.robithohmurid.app.databinding.ActivityContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.extension.view.initToolbar

class ContentActivity : BaseActivity<ActivityContentBinding, ContentViewModel>(
    ActivityContentBinding::inflate,
    ContentViewModel::class
) {

    private val contentAdapter by lazy {
        return@lazy ContentAdapter()
    }
    override fun onInitUI(savedInstanceState: Bundle?) {
        initToolbar(binding.toolbar)
        with(binding){
            rvContent.adapter = contentAdapter
        }
    }

    override fun onInitData() {
       contentAdapter.setItems(sampleContentMarkwon)
    }

}
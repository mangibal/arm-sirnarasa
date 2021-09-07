package com.robithohmurid.app.presentation.content

import android.os.Bundle
import com.robithohmurid.app.data.local.sampleContentMarkwon
import com.robithohmurid.app.data.model.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.databinding.ActivityContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.constant.IntentKey
import com.robithohmurid.app.external.extension.app.getAliasById
import com.robithohmurid.app.external.extension.app.observe
import com.robithohmurid.app.external.extension.view.initToolbar
import com.robithohmurid.app.external.extension.view.setupList

class ContentActivity : BaseActivity<ActivityContentBinding, ContentViewModel>(
    ActivityContentBinding::inflate,
    ContentViewModel::class
) {

    private val contentAdapter by lazy {
        return@lazy ContentAdapter()
    }

    private val category: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.CATEGORY_KEY) ?: ""
    }

    private val contentId: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.CONTENT_KEY) ?: ""
    }

    private val itemId: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.ITEM_KEY) ?: ""
    }

    private val title: String by lazy {
        return@lazy dataReceived?.getString(IntentKey.TITLE_KEY) ?: ""
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(binding.toolbar, title)

            contentAdapter.setListener {
//                router.gotoContent(
//                    this@ListContentActivity,
//                    category = category,
//                    content = contentId.getAliasById(),
//                    item = it.alias
//                )
            }
            rvContent.apply {
                setupList(this)
                adapter = contentAdapter
            }
        }
    }

    override fun onInitData() {
        with(viewModel) {
            observe(listItem, ::onItemReceived)

            getListItem(category, contentId, itemId)
        }
    }

    private fun onItemReceived(list: List<ItemEntity>) {
        contentAdapter.setItems(list)
    }

}
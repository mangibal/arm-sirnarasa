package com.robithohmurid.app.presentation.content

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.databinding.ActivityContentBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.constant.IntentKey
import com.robithohmurid.app.external.extension.app.observe
import com.robithohmurid.app.external.extension.view.*

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
            observe(isLoading, ::onLoadingData)
            observe(listItem, ::onItemReceived)

            getListItem(category, contentId, itemId)
        }
    }

    private fun onLoadingData(isLoading: Boolean) {
        binding.pbContent.showIf(isLoading)
    }

    private fun onItemReceived(list: List<ItemEntity>) {
        with(binding) {
            rvContent.showIf(list.isNotEmpty())
            tvMemuat.run {
                showIf(list.isEmpty())
                if (list.isEmpty()) {
                    text = getString(R.string.msg_data_kosong)
                }
            }
            contentAdapter.setItems(list)
        }
    }

}
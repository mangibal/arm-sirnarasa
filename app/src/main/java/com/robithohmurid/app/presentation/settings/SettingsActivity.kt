package com.robithohmurid.app.presentation.settings

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.ActivitySettingsBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.initToolbar
import com.robithohmurid.app.external.extension.view.onClick
import com.robithohmurid.app.external.extension.view.snackBar

class SettingsActivity : BaseActivity<ActivitySettingsBinding, SettingsViewModel>(
    ActivitySettingsBinding::inflate,
    SettingsViewModel::class
) {

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(toolbarLayout.toolbar, getString(R.string.title_pengaturan))

            viewSholat.root.onClick {
//                router.gotoSettingSholat(this@SettingsActivity)
            }

            viewFaq.apply {
                root.onClick { root.snackBar(getString(R.string.title_faq)) }
                ivIcon.setImageDrawable(getDrawableCompat(R.drawable.ic_faq))
                tvTitle.text = getString(R.string.title_faq)
            }

            viewSaran.apply {
                root.onClick { root.snackBar(getString(R.string.title_saran_masukan)) }
                ivIcon.setImageDrawable(getDrawableCompat(R.drawable.ic_saran_dan_masukan))
                tvTitle.text = getString(R.string.title_saran_masukan)
            }

            viewAbout.apply {
                root.onClick { root.snackBar(getString(R.string.title_tentang_kami)) }
                ivIcon.setImageDrawable(getDrawableCompat(R.drawable.ic_tentang_kami))
                tvTitle.text = getString(R.string.title_tentang_kami)
            }

            viewRating.apply {
                root.onClick { root.snackBar(getString(R.string.title_beri_rating)) }
                ivIcon.setImageDrawable(getDrawableCompat(R.drawable.ic_bintang))
                tvTitle.text = getString(R.string.title_beri_rating)
            }

            viewShare.apply {
                root.onClick { root.snackBar(getString(R.string.title_bagikan_app)) }
                ivIcon.setImageDrawable(getDrawableCompat(R.drawable.ic_share))
                tvTitle.text = getString(R.string.title_bagikan_app)
            }
        }
    }

    override fun onInitData() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
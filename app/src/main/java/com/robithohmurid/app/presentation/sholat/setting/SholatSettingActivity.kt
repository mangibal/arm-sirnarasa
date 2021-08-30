package com.robithohmurid.app.presentation.sholat.setting

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.ActivitySholatSettingBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.hide
import com.robithohmurid.app.external.extension.view.initToolbar
import com.robithohmurid.app.external.extension.view.show

class SholatSettingActivity : BaseActivity<ActivitySholatSettingBinding, SholatSettingViewModel>(
    ActivitySholatSettingBinding::inflate,
    SholatSettingViewModel::class
) {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        with(binding) {
            initToolbar(
                toolbar.toolbar,
                title = getString(R.string.title_setting_adzan),
                colorTitle = R.color.white
            )
            toolbar.toolbar.navigationIcon = getDrawableCompat(R.drawable.ic_back_white)

            // setup pemberitahuan adzan
            layoutNotifikasiAdzan.run {
                tvTitle.text = getString(R.string.title_pemberitahuan_adzan)
                tvSubtitle.hide()
                swSettings.hide()
            }

            // setup shortcut jadwal sholat
            layoutShortcutJadwalSholat.run {
                tvTitle.text = getString(R.string.title_shortcut_jadwal_sholat)
                tvSubtitle.text = "Aktifkan pengingat waktu sholat"
                swSettings.show()
            }

            // setup lokasi
            layoutLokasi.run {
                tvTitle.text = getString(R.string.title_lokasi)
                tvSubtitle.text = "Kecamatan Panjalu, Kab. Ciamis, Indonesia"
            }

            // setup metode waktu sholat
            layoutMetode.run {
                tvTitle.text = getString(R.string.title_metode_hitung_sholat)
                tvSubtitle.text = "SIHAT/KEMENAG (Kementerian Agama RI)"
            }

            // setup koreksi manual
            layoutKoreksi.run {
                tvTitle.text = getString(R.string.title_koreksi_manual)
                tvSubtitle.text = "2,3,2,2,2"
            }

            // setup perhitungan waktu ashar
            layoutConfigAshar.run {
                tvTitle.text = getString(R.string.title_perhitungan_ashar)
                tvSubtitle.text = "Standard (Syafi'i, Maliki, Hambali)"
            }

            // setup garis lintang
            layoutGarisLintang.run {
                tvTitle.text = getString(R.string.title_garis_lintang)
                tvSubtitle.text = "Metode berbasis sudut"
            }

            // setup waktu musim panas
            layoutMusimPanas.run {
                tvTitle.text = getString(R.string.title_waktu_musim_panas)
                tvSubtitle.text = "Auto"
            }

            // setup imsak
            layoutImsak.run {
                tvTitle.text = getString(R.string.title_imsak)
                tvSubtitle.text = "10 menit sebelum Subuh "
            }
        }
    }

    override fun onInitData() {

    }

}
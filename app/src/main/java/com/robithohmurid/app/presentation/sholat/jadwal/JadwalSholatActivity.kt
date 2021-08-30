package com.robithohmurid.app.presentation.sholat.jadwal

import android.os.Bundle
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.SholatEntity
import com.robithohmurid.app.databinding.ActivityJadwalSholatBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.constant.DateTimeFormat
import com.robithohmurid.app.external.custom.AppBar
import com.robithohmurid.app.external.extension.app.*
import com.robithohmurid.app.external.extension.view.setupList
import com.robithohmurid.app.external.extension.view.snackBar
import java.util.*
import kotlin.collections.ArrayList

class JadwalSholatActivity : BaseActivity<ActivityJadwalSholatBinding, JadwalSholatViewModel>(
    ActivityJadwalSholatBinding::inflate,
    JadwalSholatViewModel::class
), AppBar.ToolbarListener {

    private var mListSholat = ArrayList<SholatEntity>()

    private val sholatAdapter: SholatAdapter by lazy {
        return@lazy SholatAdapter()
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupHeader()
        setupLocationInfo()
        setupDateInfo()
        setupJadwalSholat()
    }

    private fun setupLocationInfo() {
        with(binding) {
            val location = prayerHelper.getLocation()
            tvCity.text = getCityName(location.latitude, location.longitude)
        }
    }

    private fun setupDateInfo() {
        with(binding) {
            val dateMonth = (getCalendarInstance().get(Calendar.MONTH) + 1)
            val currentDate = String.format("%s-%s-%s", currentYear, dateMonth, currentDay)
            val dateFormat = currentDate.formatToString(
                startFormat = DateTimeFormat.DEFAULT_DATE_FORMAT,
                endFormat = DateTimeFormat.DATE_FORMAT_WITH_DAY
            )

            tvDate.text = dateFormat
            tvIslamicDate.text = getIslamicDate()
        }
    }

    private fun setupJadwalSholat() {
        with(binding) {

            sholatAdapter.setListener {
                root.snackBar(it.toString())
            }

            rvSholat.apply {
                setupList(this)
                adapter = sholatAdapter
            }
        }
    }

    private fun setupHeader() {
        with(binding) {
            appbar.run {
                setToolbarListener(this@JadwalSholatActivity)
                setImageDrawable(R.drawable.iv_jadwal_sholat)
            }
        }
    }

    override fun onInitData() {
        mListSholat.run {
            clear()
            addAll(prayerHelper.getPrayTimeList())
        }

        sholatAdapter.setItems(mListSholat)
    }

}
package com.robithohmurid.app.presentation.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.listAmaliyah
import com.robithohmurid.app.data.local.listNews
import com.robithohmurid.app.data.local.servicesList
import com.robithohmurid.app.data.local.sholat.LocationData
import com.robithohmurid.app.data.model.SholatEntity
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.databinding.ActivityMainBinding
import com.robithohmurid.app.databinding.BottomSheetMainBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.domain.router.ActivityScreen
import com.robithohmurid.app.external.constant.DateTimeFormat
import com.robithohmurid.app.external.constant.MenuConstant.ID_DZIKIR
import com.robithohmurid.app.external.constant.MenuConstant.ID_KHOTAMAN
import com.robithohmurid.app.external.constant.MenuConstant.ID_LAINNYA
import com.robithohmurid.app.external.constant.MenuConstant.ID_MANAQIB
import com.robithohmurid.app.external.constant.MenuConstant.ID_ZIARAH
import com.robithohmurid.app.external.extension.app.*
import com.robithohmurid.app.external.extension.view.*
import com.robithohmurid.app.presentation.dialog.LocationDialogFragment
import com.robithohmurid.app.presentation.home.adapter.MenuGridAdapter
import com.robithohmurid.app.presentation.home.adapter.NewsAdapter
import com.robithohmurid.app.presentation.home.adapter.ServicesAdapter
import com.robithohmurid.app.presentation.home.manaqib.ManaqibFragment
import com.robithohmurid.app.presentation.home.menu.MenuFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

class HomeActivity : BaseActivity<ActivityMainBinding, HomeViewModel>(
    ActivityMainBinding::inflate,
    HomeViewModel::class
), LocationDialogFragment.OnLocationSelectedListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var mDoubleTapExit = false
    private var mLocationType = 0

    private var countDownTimer: CountDownTimer? = null

    private var mListSholat = mutableListOf<SholatEntity>()

    private val menuGridAdapter: MenuGridAdapter by lazy { MenuGridAdapter() }
    private val servicesAdapter: ServicesAdapter by lazy { ServicesAdapter() }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    private val mBottomSheetBinding: BottomSheetMainBinding by lazy {
        BottomSheetMainBinding.bind(binding.inclBottomSheet.root)
    }

    override fun onBackPressed() {
        if (mDoubleTapExit) {
            super.onBackPressed()
            return
        }

        mDoubleTapExit = true
        showToast(getString(R.string.msg_press_again_to_exit))

        // return to normal state
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            withContext(Dispatchers.Main) {
                mDoubleTapExit = false
            }
        }
    }

    override fun onInitUI(savedInstanceState: Bundle?) {
        setupStatusBar()
        setupPrayerSection()
        setupDateSection()
        setupBottomSheet()
        setupAmaliyahMenu()
        setupServicesMenu()
        setupNewsMenu()
    }

    private fun setupDateSection() {
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

    private fun setupStatusBar() {
        with(binding) {
            makeStatusBarTransparent()
            ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
                ivSetting.setMarginTop(insets.systemWindowInsetTop)
                insets.consumeSystemWindowInsets()
            }
        }
    }

    private fun setupPrayerSection() {
        with(binding) {
            // location
            ivLocation.onClick { showLocationDialog() }
            tvKabupaten.onClick { showLocationDialog() }
            tvKecamatan.onClick { showLocationDialog() }

            ivSetting.onClick { router.gotoSettings(this@HomeActivity) }
            cvPrayer.onClick { router.gotoJadwalSholat(this@HomeActivity) }
        }
    }

    private fun showLocationDialog() {
        try {
            LocationDialogFragment().run {
                show(supportFragmentManager, LocationDialogFragment().tag)
            }
//            val fm = supportFragmentManager
//            val fragment: DialogFragment?
//            fragment =
//                Class.forName("com.robithohmurid.app.presentation.dialog.LocationDialogFragment")
//                    .newInstance() as DialogFragment
//            val tag = fragment.javaClass.simpleName
//            val bundle = Bundle()
//            fragment.arguments = bundle
//            fragment.showNow(fm, tag)
        } catch (ex: ClassNotFoundException) {
            ex.printStackTrace()
            showToast(ex.message.toString())
        }
    }

    private fun setupAmaliyahMenu() {
        with(binding) {
            setupGridList(inclBottomSheet.rvAmaliyah, 4)
            inclBottomSheet.rvAmaliyah.adapter = menuGridAdapter

            menuGridAdapter.run {
                setItems(listAmaliyah)
                setListener {
                    showMenu(it.id, it.name)
                }
            }
        }
    }

    private fun showMenu(id: Int, title: String) {
        val toContent = router.getIntentScreen(this, ActivityScreen.Content).apply {
            putExtra("title", title)
            putExtra("id", id)
        }
        when (id) {
            ID_MANAQIB -> ManaqibFragment().run {
                show(supportFragmentManager, ManaqibFragment().tag)
            }
            ID_LAINNYA -> MenuFragment().run {
                show(supportFragmentManager, MenuFragment().tag)
            }
            ID_ZIARAH -> {
                startActivity(toContent)
            }
            ID_DZIKIR -> {
                startActivity(toContent)
            }
            ID_KHOTAMAN -> {
                startActivity(toContent)
            }
            else -> {
                val intent =
                    router.getIntentScreen(this@HomeActivity, ActivityScreen.ListContent).apply {
                        putExtra("id", id)
                        putExtra("title", title)
                    }
                startActivity(intent)
            }
        }

    }

    private fun setupServicesMenu() {
        with(binding) {
            setupGridList(inclBottomSheet.rvOtherServices, 4)
            inclBottomSheet.rvOtherServices.adapter = servicesAdapter

            servicesAdapter.run {
                setItems(servicesList)
                setListener {
                    openWebPage(it.url)
                }
            }

        }
    }

    private fun setupNewsMenu() {
        with(binding) {
            setupList(inclBottomSheet.rvNews, false)
            inclBottomSheet.rvNews.adapter = newsAdapter

            newsAdapter.run {
                setItems(listNews)
                setListener {
                    root.snackBar(it.title)
                }
            }
        }
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetBinding.clBottomSheet)
        bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                        btnBsp.text = when (newState) {
//                            BottomSheetBehavior.STATE_EXPANDED -> "Close Persistent Bottom Sheet"
//                            BottomSheetBehavior.STATE_COLLAPSED -> "Open Persistent Bottom Sheet"
//                            else -> "Persistent Bottom Sheet"
//                        }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

            })
        }
    }

    private fun calculatePrayerTime() {
        with(binding) {
            mListSholat.clear()
            mListSholat = prayerHelper.getPrayTimeList() as MutableList<SholatEntity>

            val listViewSholat = listOf(tvSubuh, tvDhuhur, tvAshar, tvMaghrib, tvIsya)

            for (i in listViewSholat.indices) {
                listViewSholat[i].text =
                    String.format("%s\n%s", mListSholat[i].name, mListSholat[i].time)
            }

            setCurrentTime()
        }
    }

    private fun setCurrentTime() {
        with(binding) {
            val cal = getCalendarInstance()
            val calSubuh = getCalendarInstance().apply {
                val date = mListSholat[0].date.split("-")
                val time = mListSholat[0].time.split(":")
                val month = if (date[1].toInt() > 0) {
                    date[1].toInt() - 1
                } else {
                    date[1].toInt()
                }
                set(
                    date[0].toInt(),
                    month,
                    date[2].toInt(),
                    time[0].toInt(),
                    time[1].toInt()
                )
            }
            val calDhuhur = getCalendarInstance().apply {
                val date = mListSholat[1].date.split("-")
                val time = mListSholat[1].time.split(":")
                val month = if (date[1].toInt() > 0) {
                    date[1].toInt() - 1
                } else {
                    date[1].toInt()
                }
                set(
                    date[0].toInt(),
                    month,
                    date[2].toInt(),
                    time[0].toInt(),
                    time[1].toInt()
                )
            }
            val calAshar = getCalendarInstance().apply {
                val date = mListSholat[2].date.split("-")
                val time = mListSholat[2].time.split(":")
                val month = if (date[1].toInt() > 0) {
                    date[1].toInt() - 1
                } else {
                    date[1].toInt()
                }
                set(
                    date[0].toInt(),
                    month,
                    date[2].toInt(),
                    time[0].toInt(),
                    time[1].toInt()
                )
            }
            val calMaghrib = getCalendarInstance().apply {
                val date = mListSholat[3].date.split("-")
                val time = mListSholat[3].time.split(":")
                val month = if (date[1].toInt() > 0) {
                    date[1].toInt() - 1
                } else {
                    date[1].toInt()
                }
                set(
                    date[0].toInt(),
                    month,
                    date[2].toInt(),
                    time[0].toInt(),
                    time[1].toInt()
                )
            }
            val calIsya = getCalendarInstance().apply {
                val date = mListSholat[4].date.split("-")
                val time = mListSholat[4].time.split(":")
                val month = if (date[1].toInt() > 0) {
                    date[1].toInt() - 1
                } else {
                    date[1].toInt()
                }
                set(
                    date[0].toInt(),
                    month,
                    date[2].toInt(),
                    time[0].toInt(),
                    time[1].toInt()
                )
            }
            when {
                cal < calSubuh -> {
                    setNextPraySection(cal, calSubuh, mListSholat[0])
                }
                cal > calSubuh && cal <= calDhuhur -> {
                    setNextPraySection(cal, calDhuhur, mListSholat[1])
                }
                cal > calDhuhur && cal <= calAshar -> {
                    setNextPraySection(cal, calAshar, mListSholat[2])
                }
                cal > calAshar && cal <= calMaghrib -> {
                    setNextPraySection(cal, calMaghrib, mListSholat[3])
                }
                cal > calMaghrib && cal <= calIsya -> {
                    setNextPraySection(cal, calIsya, mListSholat[4])
                }
                cal > calIsya -> {
                    val tomorrowCalendar = getCalendarInstance()
                    tomorrowCalendar.add(Calendar.DATE, 1)
                    val dateTimeFormat = tomorrowCalendar.time.toLocaleString()
                    val dateFormatted = dateTimeFormat.formatToString(
                        DateTimeFormat.CALENDAR_ID_FORMAT,
                        DateTimeFormat.DEFAULT_DATE_FORMAT
                    )

                    val splitted = dateFormatted.split("-")
                    val year = splitted[0].toInt()
                    val month = if (splitted[1].toInt() > 0) {
                        splitted[1].toInt() - 1
                    } else {
                        splitted[1].toInt()
                    }
                    val day = splitted[2].toInt()

                    val listSholat =
                        prayerHelper.getPrayTimeList(year, month, day) as MutableList<SholatEntity>
                    val tomorrowSubuh = listSholat[0]
                    tvPrayTime.text =
                        String.format("%s %s", tomorrowSubuh.name, tomorrowSubuh.time)
                    val splittedTime = listSholat[0].time.split(":")
                    tomorrowCalendar.set(
                        year,
                        month,
                        day,
                        splittedTime[0].toInt(),
                        splittedTime[1].toInt()
                    )

                    setNextPraySection(
                        prevCalendar = cal,
                        nextCalendar = tomorrowCalendar,
                        nextPray = tomorrowSubuh
                    )
                }
            }
        }
    }

    private fun setNextPraySection(
        prevCalendar: Calendar,
        nextCalendar: Calendar,
        nextPray: SholatEntity
    ) {
        binding.tvPrayTime.text =
            String.format("%s %s", nextPray.name, nextPray.time)
        val count = nextCalendar.timeInMillis - prevCalendar.timeInMillis
        checkTimeLeft(count)
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSION_REQUEST_ACCESS_LOCATION
            )
            return
        } else {
            mFusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val data = LocationData(location.latitude, location.longitude)
                        prayerHelper.setLocation(data)
                        setLocationInfo(data.latitude, data.longitude)
                        calculatePrayerTime()
                    } else {
                        val data = prayerHelper.getLocation()
                        setLocationInfo(data.latitude, data.longitude)
                        calculatePrayerTime()
                    }
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> requestLocationPermission()
                PackageManager.PERMISSION_DENIED -> {
                    showToast("Izin ditolak")
                    requestLocationPermission()
                }
            }
        }
    }

    override fun onInitData() {
        mLocationType = prayerHelper.getLocationType()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (mLocationType == 0) {
            requestLocationPermission()
        } else {
            val location = prayerHelper.getLocation()
            setLocationInfo(location.latitude, location.longitude)
            calculatePrayerTime()
        }

        with(viewModel) {
            observe(listContent, ::onListContentReceived)
            observe(listItem, ::onListItemReceived)

//            getListSholat()
        }
    }

    private fun onListContentReceived(data: List<ContentEntity>) {
        logInfo(data.toString())
        showToast(data.toString())
        if (data.isNotEmpty()) {
            viewModel.getListItem(data[0].alias)
        }
    }

    private fun onListItemReceived(data: List<ItemEntity>) {
        logInfo(data.toString())
        showToast(data.toString())
    }

    private fun setLocationInfo(latitude: Double, longitude: Double) {
        with(binding) {
            tvKabupaten.text = getCityName(latitude, longitude)
            tvKecamatan.text = getLocalityName(latitude, longitude)
        }
    }

    private fun checkTimeLeft(milliSecond: Long) {
        countDownTimer = object : CountDownTimer(milliSecond, 1000) {
            override fun onFinish() {
                logInfo("Finish")
            }

            override fun onTick(millisUntilFinished: Long) {
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 60

                lifecycleScope.launch(Dispatchers.IO) {
                    delay(timeMillis = 1000)
                    withContext(Dispatchers.Main) {
                        val counting =
                            String.format(
                                "%01d jam %01d menit %01d detik lagi",
                                hours,
                                minutes,
                                seconds
                            )
                        binding.tvTimeLeft.text = counting
                    }
                }
            }
        }.start()
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }

    override fun onLocationUpdated() {
        countDownTimer?.cancel()
        prayerHelper.setLocationType(0)
        requestLocationPermission()
    }

    override fun onSirnarasaSelected(locationData: LocationData) {
        countDownTimer?.cancel()
        prayerHelper.run {
            setLocationType(1)
            setLocation(locationData)
        }
        setLocationInfo(locationData.latitude, locationData.longitude)
        calculatePrayerTime()
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }

}
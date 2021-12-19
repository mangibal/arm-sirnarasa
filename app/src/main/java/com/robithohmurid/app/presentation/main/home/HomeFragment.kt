package com.robithohmurid.app.presentation.main.home

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
import com.robithohmurid.app.data.local.amaliyah.dzikirData
import com.robithohmurid.app.data.local.amaliyah.khotamanData
import com.robithohmurid.app.data.local.amaliyah.tawasulData
import com.robithohmurid.app.data.local.listAmaliyahGrid
import com.robithohmurid.app.data.local.listNews
import com.robithohmurid.app.data.local.servicesList
import com.robithohmurid.app.data.local.sholat.LocationData
import com.robithohmurid.app.data.model.entity.SholatEntity
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.databinding.BottomSheetMainBinding
import com.robithohmurid.app.databinding.FragmentHomeBinding
import com.robithohmurid.app.domain.abstraction.BaseFragment
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.DateTimeFormat
import com.robithohmurid.app.external.constant.MenuConstant
import com.robithohmurid.app.external.extension.app.*
import com.robithohmurid.app.external.extension.view.*
import com.robithohmurid.app.presentation.dialog.LocationDialogFragment
import com.robithohmurid.app.presentation.main.MainViewModel
import com.robithohmurid.app.presentation.main.adapter.MenuGridAdapter
import com.robithohmurid.app.presentation.main.adapter.NewsAdapter
import com.robithohmurid.app.presentation.main.adapter.ServicesAdapter
import com.robithohmurid.app.presentation.main.manaqib.ManaqibFragment
import com.robithohmurid.app.presentation.main.menu.MenuFragment
import com.robithohmurid.app.presentation.main.sholat.SholatFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(
    FragmentHomeBinding::inflate,
    MainViewModel::class
), LocationDialogFragment.OnLocationSelectedListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var mLocationType = 0

    private var countDownTimer: CountDownTimer? = null

    private var mListSholat = mutableListOf<SholatEntity>()

    private val menuGridAdapter: MenuGridAdapter by lazy { MenuGridAdapter() }
    private val servicesAdapter: ServicesAdapter by lazy { ServicesAdapter() }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    private val mBottomSheetBinding: BottomSheetMainBinding by lazy {
        BottomSheetMainBinding.bind(binding.inclBottomSheet.root)
    }

//    override fun attachBaseContext(newBase: Context) {
//        val localeUpdatedContext: ContextWrapper = LanguageUtils.updateLocale(newBase, localeId)
//        super.attachBaseContext(localeUpdatedContext)
//    }

    override fun onInitUI(savedInstanceState: Bundle?) {
//        setupStatusBar()
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
            requireActivity().makeStatusBarTransparent()
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

            ivSetting.onClick { router.navigateToSettings(requireActivity()) }
            cvPrayer.onClick { router.navigateToJadwalSholat(requireActivity()) }
        }
    }

    private fun showLocationDialog() {
        try {
            LocationDialogFragment().run {
                show(parentFragmentManager, LocationDialogFragment().tag)
            }
        } catch (ex: ClassNotFoundException) {
            ex.printStackTrace()
            requireContext().showToast(ex.message.toString())
        }
    }

    private fun setupAmaliyahMenu() {
        with(binding) {
            requireContext().setupGridList(inclBottomSheet.rvAmaliyah, 4)
            inclBottomSheet.rvAmaliyah.adapter = menuGridAdapter

            menuGridAdapter.run {
                setItems(listAmaliyahGrid)
                setListener {
                    showMenu(it.alias, it.name)
                }
            }
        }
    }

    private fun showMenu(alias: String, title: String) {
        when (alias) {
            MenuConstant.ADAB -> router.navigateToListContent(
                requireActivity(),
                CategoryConstant.AMALIYAH_KEY,
                alias,
                title
            )
            MenuConstant.SHOLAT -> SholatFragment().run {
                show(parentFragmentManager, SholatFragment().tag)
            }
            MenuConstant.DZIKIR -> {
                router.navigateToContent(requireActivity(), title, dzikirData.content)
            }
            MenuConstant.TAWASSUL -> {
                router.navigateToContent(requireActivity(), title, tawasulData.content)
            }
            MenuConstant.KHOTAMAN -> {
                router.navigateToContent(requireActivity(), title, khotamanData.content)
            }
            MenuConstant.MANAQIB -> ManaqibFragment().run {
                show(parentFragmentManager, ManaqibFragment().tag)
            }
            MenuConstant.DOA -> {
                router.navigateToListContent(
                    requireActivity(),
                    CategoryConstant.TQN_KEY,
                    alias,
                    title
                )
            }
            MenuConstant.SHOLAWAT -> {
                router.navigateToListContent(
                    requireActivity(),
                    category = CategoryConstant.AMALIYAH_KEY,
                    alias = alias,
                    title = title,
                )
            }
            MenuConstant.LAINNYA -> MenuFragment().run {
                show(parentFragmentManager, MenuFragment().tag)
            }
        }

    }

    private fun setupServicesMenu() {
        with(binding) {
            requireContext().setupGridList(inclBottomSheet.rvOtherServices, 4)
            inclBottomSheet.rvOtherServices.adapter = servicesAdapter

            servicesAdapter.run {
                setItems(servicesList)
                setListener {
                    requireContext().openWebPage(it.url)
                }
            }

        }
    }

    private fun setupNewsMenu() {
        with(binding) {
            requireContext().setupList(inclBottomSheet.rvNews, false)
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
//            val date = currentDate
            val year = cal.getYearByCalendar()
            val months = cal.getMonthByCalendar()
            val day = cal.getDayByCalendar()

            val timeFormatter = org.joda.time.format.DateTimeFormat.forPattern("HH:mm")
            val currentTime = timeFormatter.parseLocalTime(currentTimeByMinute)
            val subuhTime = timeFormatter.parseLocalTime(mListSholat[0].time)
            val zuhurTime = timeFormatter.parseLocalTime(mListSholat[1].time)
            val asarTime = timeFormatter.parseLocalTime(mListSholat[2].time)
            val magribTime = timeFormatter.parseLocalTime(mListSholat[3].time)
            val isyaTime = timeFormatter.parseLocalTime(mListSholat[4].time)

            val isSubuh = currentTime < subuhTime
            val isZuhur = currentTime > subuhTime && currentTime <= zuhurTime
            val isAsar = currentTime > zuhurTime && currentTime <= asarTime
            val isMagrib = currentTime > asarTime && currentTime <= magribTime
            val isIsya = currentTime > magribTime && currentTime <= isyaTime
            val isNextSubuh = currentTime > isyaTime

            val calSubuh = getCalendarInstance().apply {
                val time = mListSholat[0].time.split(":")
                val hour = time[0].toInt()
                val minute = time[1].toInt()
                val month = if (months > 0) {
                    months - 1
                } else {
                    months
                }
                set(
                    year,
                    month,
                    day,
                    hour,
                    minute
                )
            }
            val calDhuhur = getCalendarInstance().apply {
                val time = mListSholat[1].time.split(":")
                val hour = time[0].toInt()
                val minute = time[1].toInt()
                val month = if (months > 0) {
                    months - 1
                } else {
                    months
                }
                set(
                    year,
                    month,
                    day,
                    hour,
                    minute
                )
            }
            val calAshar = getCalendarInstance().apply {
                val time = mListSholat[2].time.split(":")
                val hour = time[0].toInt()
                val minute = time[1].toInt()
                val month = if (months > 0) {
                    months - 1
                } else {
                    months
                }
                set(
                    year,
                    month,
                    day,
                    hour,
                    minute
                )
            }
            val calMaghrib = getCalendarInstance().apply {
                val time = mListSholat[3].time.split(":")
                val hour = time[0].toInt()
                val minute = time[1].toInt()
                val month = if (months > 0) {
                    months - 1
                } else {
                    months
                }
                set(
                    year,
                    month,
                    day,
                    hour,
                    minute
                )
            }
            val calIsya = getCalendarInstance().apply {
                val time = mListSholat[4].time.split(":")
                val hour = time[0].toInt()
                val minute = time[1].toInt()
                val month = if (months > 0) {
                    months - 1
                } else {
                    months
                }
                set(
                    year,
                    month,
                    day,
                    hour,
                    minute
                )
            }
            when {
                isSubuh -> {
                    setNextPraySection(cal, calSubuh, mListSholat[0])
                }
                isZuhur -> {
                    setNextPraySection(cal, calDhuhur, mListSholat[1])
                }
                isAsar -> {
                    setNextPraySection(cal, calAshar, mListSholat[2])
                }
                isMagrib -> {
                    setNextPraySection(cal, calMaghrib, mListSholat[3])
                }
                isIsya -> {
                    setNextPraySection(cal, calIsya, mListSholat[4])
                }
                isNextSubuh -> {
                    val tomorrowCalendar = getCalendarInstance()
                    tomorrowCalendar.add(Calendar.DATE, 1)
                    val year = tomorrowCalendar.getYearByCalendar()
                    val month = tomorrowCalendar.getMonthByCalendar()
                    val day = tomorrowCalendar.getDayByCalendar()

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
//        checkTimeLeft(count)
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
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
                    requireContext().showToast("Izin ditolak")
                    requestLocationPermission()
                }
            }
        }
    }

    override fun onInitData() {
        mLocationType = prayerHelper.getLocationType()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

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
        requireContext().showToast(data.toString())
        if (data.isNotEmpty()) {
            viewModel.getListItem(data[0].alias)
        }
    }

    private fun onListItemReceived(data: List<ItemEntity>) {
        logInfo(data.toString())
        requireContext().showToast(data.toString())
    }

    private fun setLocationInfo(latitude: Double, longitude: Double) {
        with(binding) {
            tvKabupaten.text = requireContext().getCityName(latitude, longitude)
            tvKecamatan.text = requireContext().getLocalityName(latitude, longitude)
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
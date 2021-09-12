package com.robithohmurid.app.data.local

import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.entity.MenuEntity
import com.robithohmurid.app.data.model.entity.ContentEntity
import com.robithohmurid.app.data.model.entity.ListingContentEntity

/**
 * Created by Iqbal Fauzi on 18/06/21 20.16
 * iqbal.fauzi.if99@gmail.com
 */
data class NewsEntity(
    var id: Int = 0,
    var title: String = "Maulid Abah Aos ke 79",
    var date: String = "Ahad, 14 Ramadhan 1442H",
    var imageUrl: Int = R.drawable.iv_home
)

val listNews = listOf(
    NewsEntity(),
    NewsEntity(),
    NewsEntity(),
)

data class ServicesEntity(
    var id: Int = 0,
    var name: String = "Web STID Sirnarasa",
    var icon: Int = R.drawable.ic_stid,
    var url: String = "https://www.stidsirnarasa.ac.id/"
)

val servicesList = listOf(
    ServicesEntity(),
    ServicesEntity(
        1,
        "SIAKAD STID SIRNARASA",
        R.drawable.ic_siakad,
        "https://siakadbjbs.stidsirnarasa.ac.id/"
    )
)
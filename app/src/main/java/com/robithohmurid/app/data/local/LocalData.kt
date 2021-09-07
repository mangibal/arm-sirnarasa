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

val listMenuTqn = listOf(
    MenuEntity(
        8, "Tujuan dan Dasar TQN", "Tujuan dan Dasar TQN Suryalaya",
        R.drawable.ic_tqn
    ),
    MenuEntity(
        9,
        "Diagram Latifah",
        "Latifah yang ada alam qolbu manusia",
        R.drawable.ic_latifah
    ),
    MenuEntity(
        10,
        "Dustur",
        "Langgam bacaan do'a pagi hari",
        R.drawable.ic_dustur
    ),
    MenuEntity(11, "Tarhim", "Langgam bacaan do'a", R.drawable.ic_tarhim),
    MenuEntity(
        12, "Wakil Talqin", "Wakil talqin Pangersa Abah Aos QS",
        R.drawable.ic_waktal
    ),
    MenuEntity(
        13,
        "Silsilah dan Sanad",
        "Silsilah TQN PPS dan Sanad Pangersa Abah Aos",
        R.drawable.ic_silsilah
    ),
    MenuEntity(
        14,
        "Syekh Abdul Qodir Al-Jailani",
        "Profil dan Nama-nama Syekh Abdul Qodir",
        R.drawable.ic_manaqib
    ),
    MenuEntity(
        15,
        "Maklumat",
        "Maklumat terbaru dari Pangersa Abah Aos QS",
        R.drawable.ic_maklumat
    )
)

val listMenuManaqib = listOf(
    MenuEntity(16, "MC", icon = R.drawable.ic_mic),
    MenuEntity(17, "Sholawat", icon = R.drawable.ic_sholawat),
    MenuEntity(18, "Tanbih"),
    MenuEntity(19, "Tawashul", icon = R.drawable.ic_tawashul),
    MenuEntity(20, "Manqobah", icon = R.drawable.ic_manqobah)
)

val listNews = listOf(
    NewsEntity(),
    NewsEntity(),
    NewsEntity(),
)
val listContentAdab: List<ListingContentEntity> = listOf(
    ListingContentEntity(1, "Adab Berwudhu"),
    ListingContentEntity(2, "Adab Sholat"),
    ListingContentEntity(3, "Adab Pergi ke Masjid"),
    ListingContentEntity(4, "Adab Mendengarkan Adzan"),
    ListingContentEntity(5, "Adab Iqomah"),
    ListingContentEntity(6, "Adab Hari Jumat"),
    ListingContentEntity(7, "Adab Berdoa"),
    ListingContentEntity(8, "Adab Masuk Rumah"),
    ListingContentEntity(9, "Adab Keluar Rumah"),
    ListingContentEntity(10, "Adab Dzikir"),
)

val sampleContentMarkwon: List<ContentEntity> = listOf(
    ContentEntity(
        1, "Membasuh Wajah", """
# Structured documents

Sometimes it's useful to have different levels of headings to structure your documents. Start lines with a `#` to create headings. Multiple `##` in a row denote smaller heading sizes.

### This is a third-tier heading

You can use one `#` all the way up to `######` six for different heading sizes.

If you'd like to quote someone, use the > character before the line:

> Coffee. The finest organic suspension ever devised... I beat the Borg with it.
> - Captain Janeway
Sometimes you want numbered lists:

1. One
2. Two
3. Three

Sometimes you want bullet points:

* Start a line with a star
* Profit!

Alternatively,

- Dashes work just as well
- And if you have sub points, put two spaces before the dash or star:
  - Like this
  - And this
  
First Header | Second Header
------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column

<em>This is emphasized text!</em>

<strong>This is strong text!</strong>

<em>This is emphasized text!</em>

<strong>This is strong text!</strong>
    """.trimIndent()
    ),
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
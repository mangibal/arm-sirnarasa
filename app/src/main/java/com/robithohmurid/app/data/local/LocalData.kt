package com.robithohmurid.app.data.local

import android.os.Parcelable
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.ContentEntity
import com.robithohmurid.app.data.model.ListingContentEntity
import kotlinx.parcelize.Parcelize

/**
 * Created by Iqbal Fauzi on 18/06/21 20.16
 * iqbal.fauzi.if99@gmail.com
 */
@Parcelize
data class AmaliyahEntity(
    var id: Int = 0,
    var name: String = "Sholat",
    var subtitle: String = "Sholat harian, bulanan, dan tahunan",
    var icon: Int = R.drawable.ic_kabah
) : Parcelable

data class NewsEntity(
    var id: Int = 0,
    var title: String = "Maulid Abah Aos ke 79",
    var date: String = "Ahad, 14 Ramadhan 1442H",
    var imageUrl: Int = R.drawable.iv_home
)

val listAmaliyah = listOf(
    AmaliyahEntity(),
    AmaliyahEntity(1, "Dzikir", "Dzikir harian", R.drawable.ic_dzikir),
    AmaliyahEntity(2, "Khotaman", "Khotaman TQN PPS Suryalaya", R.drawable.ic_khotaman),
    AmaliyahEntity(
        3,
        "Manaqib",
        "Manaqib Tuan Syaikh Abdul Qadir Jailani QS",
        R.drawable.ic_manaqib
    ),
    AmaliyahEntity(4, "Adab-adab", "Adab sehari-hari", R.drawable.ic_adab),
    AmaliyahEntity(5, "Doa-doa", "Kumpulan doa-doa mustajab", R.drawable.ic_doa),
    AmaliyahEntity(6, "Ziarah", "Ziarah Kubur", R.drawable.ic_ziarah),
    AmaliyahEntity(7, "Lainnya", icon = R.drawable.ic_lainnya)
)

val listMenuTqn = listOf(
    AmaliyahEntity(
        8, "Tujuan dan Dasar TQN", "Tujuan dan Dasar TQN Suryalaya",
        R.drawable.ic_tqn
    ),
    AmaliyahEntity(
        9,
        "Diagram Latifah",
        "Latifah yang ada alam qolbu manusia",
        R.drawable.ic_latifah
    ),
    AmaliyahEntity(
        10,
        "Dustur",
        "Langgam bacaan do'a pagi hari",
        R.drawable.ic_dustur
    ),
    AmaliyahEntity(11, "Tarhim", "Langgam bacaan do'a", R.drawable.ic_tarhim),
    AmaliyahEntity(
        12, "Wakil Talqin", "Wakil talqin Pangersa Abah Aos QS",
        R.drawable.ic_waktal
    ),
    AmaliyahEntity(
        13,
        "Silsilah dan Sanad",
        "Silsilah TQN PPS dan Sanad Pangersa Abah Aos",
        R.drawable.ic_silsilah
    ),
    AmaliyahEntity(
        14,
        "Syekh Abdul Qodir Al-Jailani",
        "Profil dan Nama-nama Syekh Abdul Qodir",
        R.drawable.ic_manaqib
    ),
    AmaliyahEntity(
        15,
        "Maklumat",
        "Maklumat terbaru dari Pangersa Abah Aos QS",
        R.drawable.ic_maklumat
    )
)

val listMenuManaqib = listOf(
    AmaliyahEntity(16, "MC", icon = R.drawable.ic_mic),
    AmaliyahEntity(17, "Sholawat", icon = R.drawable.ic_sholawat),
    AmaliyahEntity(18, "Tanbih"),
    AmaliyahEntity(19, "Tawashul", icon = R.drawable.ic_tawashul),
    AmaliyahEntity(20, "Manqobah", icon = R.drawable.ic_manqobah)
)

val listNews = listOf(
    NewsEntity(),
    NewsEntity(),
    NewsEntity(),
)
val listContentAdab : List<ListingContentEntity> = listOf(
    ListingContentEntity(1,"Adab Berwudhu"),
    ListingContentEntity(2,"Adab Sholat"),
    ListingContentEntity(3,"Adab Pergi ke Masjid"),
    ListingContentEntity(4,"Adab Mendengarkan Adzan"),
    ListingContentEntity(5,"Adab Iqomah"),
    ListingContentEntity(6,"Adab Hari Jumat"),
    ListingContentEntity(7,"Adab Berdoa"),
    ListingContentEntity(8,"Adab Masuk Rumah"),
    ListingContentEntity(9,"Adab Keluar Rumah"),
    ListingContentEntity(10,"Adab Dzikir"),
)

val sampleContentMarkwon : List<ContentEntity> = listOf(
    ContentEntity(1,"Membasuh Wajah","""
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
    """.trimIndent()),
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
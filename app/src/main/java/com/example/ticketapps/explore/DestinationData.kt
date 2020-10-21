package com.example.ticketapps.explore

import java.util.ArrayList

object DestinationData {
    private val city_name = arrayOf("Tokyo",
        "Seoul",
        "Melbourne",
        "Maldives",
        "Phuket",
        "Singapura",
        "Malaysia",
        "Bangkok",
        "Swiss",
        "Norwegia")

    private val country_name = arrayOf("Japan",
        "South Korea",
        "Australia",
        "Samudera Hindia",
        "Thailand",
        "Singapura",
        "Malaysia",
        "Thailand",
        "Swiss",
        "Norwegia")

    private val image = arrayOf("https://cdnaz.cekaja.com/media/2019/11/tokyo.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/seoul.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/melbourne.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/Maldives.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/phuket.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/Singapura.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/Malaysia.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/Bangkok.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/swiss.jpg",
        "https://cdnaz.cekaja.com/media/2019/11/norwegia.jpg")

    val listData: ArrayList<DestinationModel>
        get() {
            val list = arrayListOf<DestinationModel>()
            for (position in city_name.indices) {
                val destination = DestinationModel()
                destination.city_name = city_name[position]
                destination.country_name = country_name[position]
                destination.image = image[position]
                list.add(destination)
            }
            return list
        }
}
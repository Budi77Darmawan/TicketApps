package com.example.ticketapps.searchResult

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchResultViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val listTicketLiveData = MutableLiveData<List<TicketsModel>>()
    val countTicketsLiveData = MutableLiveData<Int>()

    private lateinit var service: SearchResultApiService

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setSearchResultService(service: SearchResultApiService) {
        this.service = service
    }

    fun callSearchResultApi(city_destination: String, city_departure: String, order_class: String, passengger: String) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getTicketRequest(city_destination, city_departure, order_class, passengger)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is SearchResultResponse) {
                withContext(Dispatchers.Main) {
                    listTicketLiveData.value = response.data?.map {
                        TicketsModel(
                            it.idPlane,
                            it.planeImage,
                            it.price,
                            it.codeDepature,
                            it.cityDepature,
                            it.codeCountryDepature,
                            it.countryDepature,
                            it.codeDestinantion,
                            it.cityDestinantion,
                            it.codeCountryDestinantion,
                            it.countryDestinantion
                        )
                    }
                    countTicketsLiveData.value = response.data?.size
                }
            }
            isLoadingLiveData.value = false
        }
    }
}
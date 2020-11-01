package com.example.ticketapps.searchFlight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchFlightViewModel : ViewModel(), CoroutineScope {
    val dataFlightLiveData = MutableLiveData<DataFlightModel>()

    private lateinit var service: AirportApiService

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setSearchFlightService(service: AirportApiService) {
        this.service = service
    }

    fun getDataFlightApi(city_destination: String, city_departure: String) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getDataFlight(city_destination, city_departure)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is DataFlightResponse) {
                withContext(Dispatchers.Main) {
                    val data = response.data?.map {
                        DataFlightModel(
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
                    dataFlightLiveData.value = data?.get(0)
                }
            }
        }
    }
}
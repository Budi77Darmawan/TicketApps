package com.example.ticketapps.flightDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FlightDetailViewModel: ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isFlightLiveData = MutableLiveData<Boolean>()
    val isMessageLiveData = MutableLiveData<String>()

    private lateinit var service: FlightDetailApiService
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setRegisterService(service: FlightDetailApiService) {
        this.service = service
    }

    fun callFlightDetailApi(order_name: String?, total_price: Int?,  id_plane: Int?,passengger: String?,order_class:String, depature: String?,destination:String?,times_flight:String) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.orderFlightRequest(order_name, total_price,  id_plane, passengger,order_class, depature,destination,times_flight)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    isFlightLiveData.value = false
                }
            }
            if (response is FlightDetailResponse) {
                isMessageLiveData.value = response.message
                isFlightLiveData.value = true
            }
            isLoadingLiveData.value = false
        }
    }
}
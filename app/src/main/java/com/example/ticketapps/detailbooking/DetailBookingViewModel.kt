package com.example.ticketapps.detailbooking

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailBookingViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isDetailBookingLiveData = MutableLiveData<Boolean>()
    val listLiveData = MutableLiveData<List<DetailBookingModel>>()

    private lateinit var service: DetailBookingApiService

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setDetailBookingService(service: DetailBookingApiService) {
        this.service = service
    }


    fun callDetailBookingApi(id: Int) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getDetailBookingID(id)

                } catch (e: Throwable) {
                    e.printStackTrace()
                    withContext(Job() + Dispatchers.Main) {
                        isDetailBookingLiveData.value = false
                    }
                }
            }
            Log.d("ID Account", "$id")
            Log.d("Boolean Response", (response is DetailBookingResponse).toString())
            if (response is DetailBookingResponse) {
                val data = response.data
                val list =
                    DetailBookingModel(
                        data.id_order,
                        data.id_account,
                        data.order_name,
                        data.prince,
                        data.order_class,
                        data.passangger,
                        data.destination,
                        data.depature,
                        data.times_flight
                    )

                listLiveData.value = listOf(list)
                isDetailBookingLiveData.value = true
            }
            isLoadingLiveData.value = false
        }
    }

}
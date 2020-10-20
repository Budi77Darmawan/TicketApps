package com.example.ticketapps.searchResult

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketapps.util.sharedpref.SharedPrefProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchResultViewModel : ViewModel(), CoroutineScope {

    val isLoadingLiveData = MutableLiveData<Boolean>()
    val listTicketLiveData = MutableLiveData<List<TicketsModel>>()
    val countTicketsLiveData = MutableLiveData<Int>()

    private lateinit var service: SearchResultApiService
    private lateinit var sharedPref: SharedPrefProvider

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setSearchResultService(service: SearchResultApiService) {
        this.service = service
    }

    fun putSharedPreferences(mContext: Context) {
        sharedPref = SharedPrefProvider(mContext)
    }

    fun callSearchResultApi() {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getTicketRequest("JFK", "CGK", "economy", "adult")
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is SearchResultResponse) {
                withContext(Dispatchers.Main) {
                    val list = response.data?.map {
                        TicketsModel(
                            it.cityDeparture,
                            it.timeDeparture,
                            it.cityDestination,
                            it.timeDestination,
                            it.price,
                            it.planeImage
                        )
                    } ?: listOf()
                    listTicketLiveData.value = list
                    countTicketsLiveData.value = response.data?.size
                }
            }
            isLoadingLiveData.value = false
        }
    }
}
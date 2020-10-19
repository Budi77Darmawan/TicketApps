package com.example.ticketapps.mybooking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketapps.OrderApiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MyBookingViewModel: ViewModel(), CoroutineScope {
    private lateinit var service: OrderApiService
    var listLiveData = MutableLiveData(listOf<OrderModel>())

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setOrderService(service: OrderApiService) {
        this.service = service
    }

    fun getOrderApi() {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getOrderRequest()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is OrderResponse) {
                listLiveData.value = response.data?.map {
                    OrderModel(
                        it.id_order.orEmpty(),
                        it.order_name.orEmpty(),
                        it.status_payment.orEmpty(),
                        it.price.orEmpty(),
                        it.order_class.orEmpty(),
                        it.time_flight.orEmpty(),
                        it.code_depature.orEmpty(),
                        it.city_depature.orEmpty(),
                        it.code_country_depature.orEmpty(),
                        it.country_depature.orEmpty(),
                        it.code_destination.orEmpty(),
                        it.city_destination.orEmpty(),
                        it.code_country_destination.orEmpty(),
                        it.country_destination.orEmpty(),
                        it.plane_name.orEmpty(),
                        it.plane_image.orEmpty()
                    )
                }
            }
        }
    }
}
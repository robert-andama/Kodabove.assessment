package com.kodabove.assessment.ui.api

import com.kodabove.assessment.ui.models.Events
import com.kodabove.assessment.ui.models.Schedules
import com.kodabove.assessment.ui.utils.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("getEvents")
    fun getEventsList(): Observable<List<Events>>

    @GET("getSchedules")
    fun getSchedulesList(): Observable<List<Schedules>>

    companion object Factory {

        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}
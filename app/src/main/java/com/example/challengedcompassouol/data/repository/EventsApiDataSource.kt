package com.example.challengedcompassouol.data.repository

import com.example.challengedcompassouol.data.EventsResult
import com.example.challengedcompassouol.data.model.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsApiDataSource : EventsRepository{

    override fun getEvents(eventsResultCallback: (result: EventsResult) -> Unit) {
        ApiService.service.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                when {
                    response.isSuccessful -> {
                        val events: MutableList<Event> = mutableListOf()

                        response.body()?.let { eventBodyResponse ->
                            for (result in eventBodyResponse) {
                                val event = result.getEventModel()
                                events.add(event)
                            }
                        }

                        eventsResultCallback(EventsResult.Success(events))
                    }
                    else -> eventsResultCallback(EventsResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                eventsResultCallback(EventsResult.ServerError)
            }
        })
    }
}
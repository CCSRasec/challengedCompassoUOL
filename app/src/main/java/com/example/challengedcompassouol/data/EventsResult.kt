package com.example.challengedcompassouol.data

import com.example.challengedcompassouol.data.model.Event

sealed class EventsResult {
    class Success(val events: List<Event>) : EventsResult()
    class ApiError(val statusCode: Int) : EventsResult()
    object ServerError : EventsResult()
}
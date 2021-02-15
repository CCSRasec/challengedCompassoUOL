package com.example.challengedcompassouol.data.repository

import com.example.challengedcompassouol.data.EventsResult

interface EventsRepository {
    fun getEvents(eventsResultCallback: (result: EventsResult) -> Unit)
}
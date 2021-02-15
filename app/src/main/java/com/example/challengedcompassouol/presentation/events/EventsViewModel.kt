package com.example.challengedcompassouol.presentation.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challengedcompassouol.R
import com.example.challengedcompassouol.data.EventsResult
import com.example.challengedcompassouol.data.model.Event
import com.example.challengedcompassouol.data.repository.EventsRepository

class EventsViewModel(private val dataSource: EventsRepository) : ViewModel()  {

    val eventsLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getEvents() {
        dataSource.getEvents { result: EventsResult ->
            when (result) {
                is EventsResult.Success -> {
                    eventsLiveData.value = result.events
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_EVENTS, null)
                }
                is EventsResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.event_error_401)
                    } else {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.event_error_400_generic)
                    }
                }
                is EventsResult.ServerError -> {
                    viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.event_error_500_generic)
                }
            }
        }
    }



    class ViewModelFactory(private val dataSource: EventsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
                return EventsViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_EVENTS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}
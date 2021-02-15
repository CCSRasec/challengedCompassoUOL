package com.example.challengedcompassouol.presentation.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengedcompassouol.R
import com.example.challengedcompassouol.data.repository.EventsApiDataSource
import com.example.challengedcompassouol.presentation.base.BaseActivity
import com.example.challengedcompassouol.presentation.events_details.EventDetailsActivity
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.include_toolbar.*

class EventsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        setupToolbar(toolbarMain, R.string.event_title)

        val viewModel: EventsViewModel = EventsViewModel.ViewModelFactory(EventsApiDataSource())
            .create(EventsViewModel::class.java)

        viewModel.eventsLiveData.observe(this, Observer {
            it?.let { events ->
                with(recyclerEvents) {
                    layoutManager = LinearLayoutManager(this@EventsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = EventsAdapter(events) { event ->
                        val intent = EventDetailsActivity.getStartIntent(this@EventsActivity, event.title, event.description, event.price.toString())
                        this@EventsActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getEvents()

    }
}
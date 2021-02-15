package com.example.challengedcompassouol.presentation.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengedcompassouol.R
import com.example.challengedcompassouol.data.model.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_event.view.*

class EventsAdapter(
    private val events: List<Event>,
    private val onItemClickListener: ((event: Event) -> Unit)
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, view: Int): EventsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventsViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = events.count()

    override fun onBindViewHolder(viewHolder: EventsViewHolder, position: Int) {
        viewHolder.bindView(events[position])
    }

    class EventsViewHolder(
        itemView: View,
        private val onItemClickListener: ((event: Event) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.textTitleEvent
        private val description = itemView.textDescriptionEvent
        private var urlavatar: String? = null
        private val imageView: ImageView = itemView.findViewById(R.id.imgevent)

        fun bindView(event: Event) {
            title.text = event.title
            description.text = event.description
            urlavatar = event.image

            Picasso.get().load(urlavatar).into(imageView)

            itemView.setOnClickListener {
                onItemClickListener.invoke(event)
            }
        }
    }
}
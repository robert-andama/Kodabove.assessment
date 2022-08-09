package com.kodabove.assessment.ui.events.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kodabove.assessment.R
import com.kodabove.assessment.ui.events.adapters.EventsAdapter.EventsViewHolder
import com.kodabove.assessment.ui.models.Events

class EventsAdapter(
    private val context: Context,
    private val list: MutableList<Events>,
    fragment: Fragment,
) : RecyclerView.Adapter<EventsViewHolder>() {

    private val listener: OnItemClickListener

    init {
        this.listener = fragment as OnItemClickListener
    }

    interface OnItemClickListener {
        fun itemRemoveClick(events: Events)
        fun itemDetail(events: Events)
    }

    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: ConstraintLayout = itemView.findViewById(R.id.item_layout)
        val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
        val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        val eventSubtitle: TextView = itemView.findViewById(R.id.eventSubtitle)
        val eventDate: TextView = itemView.findViewById(R.id.eventDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_events_layout, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val eventItem = list[position]

        Glide.with(context).load(eventItem.imageUrl).into(holder.eventImage)
        holder.eventTitle.text = eventItem.title
        holder.eventSubtitle.text = eventItem.subtitle
        holder.eventDate.text = eventItem.date

        holder.layout.setOnClickListener {
            listener.itemDetail(eventItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
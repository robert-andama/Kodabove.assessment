package com.kodabove.assessment.ui.schedule.adapters

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
import com.kodabove.assessment.ui.models.Schedules
import com.kodabove.assessment.ui.schedule.adapters.SchedulesAdapter.SchedulesViewHolder

class SchedulesAdapter(
    private val context: Context,
    private val list: MutableList<Schedules>,
    fragment: Fragment,
) : RecyclerView.Adapter<SchedulesViewHolder>() {

    private val listener: OnItemClickListener

    init {
        this.listener = fragment as OnItemClickListener
    }

    interface OnItemClickListener {
        fun itemRemoveClick(Schedules: Schedules)
        fun itemDetail(Schedules: Schedules)
    }

    class SchedulesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: ConstraintLayout = itemView.findViewById(R.id.item_layout)
        val scheduleImage: ImageView = itemView.findViewById(R.id.scheduleImage)
        val scheduleTitle: TextView = itemView.findViewById(R.id.scheduleTitle)
        val scheduleSubtitle: TextView = itemView.findViewById(R.id.scheduleSubtitle)
        val scheduleDate: TextView = itemView.findViewById(R.id.scheduleDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_schedule_layout, parent, false)
        return SchedulesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SchedulesViewHolder, position: Int) {
        val scheduleItem = list[position]

        Glide.with(context).load(scheduleItem.imageUrl).into(holder.scheduleImage)
        holder.scheduleTitle.text = scheduleItem.title
        holder.scheduleSubtitle.text = scheduleItem.subtitle
        holder.scheduleDate.text = scheduleItem.date

        holder.layout.setOnClickListener {
            listener.itemDetail(scheduleItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
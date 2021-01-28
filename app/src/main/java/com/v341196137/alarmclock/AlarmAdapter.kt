package com.v341196137.alarmclock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.alarm_item.view.*
import android.widget.Switch
// not alarm data something else i believe but we'll see
class AlarmAdapter(private val alarm_list: List<AlarmData>) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {
    // only be called one time for each item on screen, and if scrolled will be ready
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // alarm_item layout, with view object
        // parent is just the recycleview as we stated before
        val alarm_item = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item,
        parent, false) // most confusing line must go understand it afterwards
        return ViewHolder(alarm_item)
    }
    // over and over again to update items with data and scrolling data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current_alarm = alarm_list[position]
        holder.time_text_view.text = (current_alarm.alarm_time)

    }
    override fun getItemCount() = alarm_list.size

    class ViewHolder(alarm_item: View) : RecyclerView.ViewHolder(alarm_item){ // will contain instance of AlarmData --> alarm_item.xml
        // time_text_view : TextView
        // alarm_switch : Switch
        // Cache view holder
        val time_text_view : TextView = alarm_item.time_text_view
        val alarm_switch : Switch = alarm_item.alarm_switch
        // idk why we need to do it like this, but in main class we don't we can just do time_text_view with view import
        // i believe it's because it's not attached to activity_main.xml...
        // ahhhh because we're caching the holder since we call it over and over again
        // instead of calling FindViewById many many times, we just call it once and hold it in the view holder
        // Then we can understand what every single view will do
    }
}

package com.v341196137.alarmclock
// Kotlin libraries

// Android libraries
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import kotlinx.android.synthetic.main.activity_main.*
import java.util.LinkedList

// do most of our work in here
// main hub for changing layouts and views and stuff
// buttononclick events to change layouts when clicked on
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var alarmList = LinkedList<AlarmData>()

        //bad coding coventions B) (probably should use binding later)
        // we can get rid all of this because just calling alarm_button == findViewById(R.Id.alarm_button) As Button
        val alarmButton: Button = findViewById(R.id.alarm_button)
        val timerButton: Button = findViewById(R.id.timer_button)
        val stopwatchButton: Button = findViewById(R.id.stopwatch_button)
        val addButton: Button = findViewById(R.id.add_alarm_button) // add button


        //button listeners
        // TODO: figure out if to use .setContentView or .addView
        // Button listeners on acitivty_main.xml
        alarmButton.setOnClickListener() {switchToView(R.layout.activity_main) } //currently operating under the assumption main view is alarm view
        timerButton.setOnClickListener() { switchToView(R.layout.timer_view)}
        stopwatchButton.setOnClickListener() { switchToView(R.layout.stopwatch_view) }
        add_alarm_button.setOnClickListener(){ switchToView(R.layout.add_alarm_view) }
        // Button listeners on add_alarm_view.xml
    }
    private fun generateList() : LinkedList<AlarmData> {
        var alarmList = LinkedList<AlarmData>()
        alarmList.add(AlarmData("12:00")) // change "12:00" to string where time is specified

        return alarmList
    }

    /**
     * Sets the context view to the passed in view
     * @param view the Int ID for the view
     */
    private fun switchToView(view: Int){
        setContentView(view)
    }

}
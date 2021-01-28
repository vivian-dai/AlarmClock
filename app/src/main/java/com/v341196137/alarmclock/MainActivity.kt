package com.v341196137.alarmclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// do most of our work in here
// main hub for changing layouts and views and stuff
// buttononclick events to change layouts when clicked on
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        print(R.id.time_text_view)

        //bad coding coventions B) (probably should use binding later)
        val alarmButton: Button = findViewById(R.id.alarm_button)
        val timerButton: Button = findViewById(R.id.timer_button)
        val stopwatchButton: Button = findViewById(R.id.stopwatch_button)

        //button listeners
        alarmButton.setOnClickListener(){ switchToAlarm()}
        timerButton.setOnClickListener(){switchToTimer()}
        stopwatchButton.setOnClickListener(){ switchToStopwatch()}

    }
}


//TODO: hahahaha implement all this
fun switchToAlarm(){

}

fun switchToTimer(){

}

fun switchToStopwatch(){

}
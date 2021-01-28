package com.v341196137.alarmclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// do most of our work in here
// main hub for changing layouts and views and stuff
// buttononclick events to change layouts when clicked on
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        print(R.id.time_text_view)
    }
}
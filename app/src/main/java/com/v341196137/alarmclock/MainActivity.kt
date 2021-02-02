package com.v341196137.alarmclock
// Kotlin libraries

// Android libraries
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

// do most of our work in here
// main hub for changing layouts and views and stuff
// buttononclick events to change layouts when clicked on
class MainActivity : AppCompatActivity() {
    private val FIVE_HOURS: Long = 18000000
    private var stopwatchRunning: Boolean = false
    private var stopwatchInitiated: Boolean = false
    private var timeDifference: Long = 0
    private var lastTime: Long = 0
    private lateinit var stopwatch: Chronometer
    private lateinit var timer: Chronometer
    private var timerRunning: Boolean = false
    private var timerTimeDifference: Long = 0
    private var lastTimerTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //something sketchy we should all ignore
        setContentView(R.layout.stopwatch_view)
        stopwatch = findViewById(R.id.stopwatch_chronometer)
        setContentView(R.layout.timer_view)
        timer = findViewById(R.id.timer_chronometer)

        setContentView(R.layout.activity_main)

        //variables
        var alarmList = LinkedList<AlarmData>()
        initiateAlarmViewButtons()

    }

    /**
     * Hi Sherwin you should javadoc this :P
     * @return something something
     */
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

    /**
     * Initiates the three buttons at the bottom necessary for navigating
     */
    private fun initiateNavButtons(){
        val alarmButton: Button = findViewById(R.id.alarm_button)
        val timerButton: Button = findViewById(R.id.timer_button)
        val stopwatchButton: Button = findViewById(R.id.stopwatch_button)
        //button listeners
        // TODO: figure out if to use .setContentView or .addView
        // Button listeners on acitivty_main.xml
        alarmButton.setOnClickListener() {
            Toast.makeText(this, "alarm button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.activity_main)
            initiateAlarmViewButtons()
        } //currently operating under the assumption main view is alarm view
        timerButton.setOnClickListener() {
            Toast.makeText(this, "timer button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.timer_view)
            initiateTimerView()
        }
        stopwatchButton.setOnClickListener() {
            Toast.makeText(this, "stopwatch button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.stopwatch_view)
            initiateStopwatch()
        }
    }

    /**
     * Does all the button initializations necessary when switching to alarm view
     */
    private fun initiateAlarmViewButtons(){
        initiateNavButtons()
        val addAlarmButton: Button = findViewById(R.id.add_alarm_button)
        addAlarmButton.setOnClickListener(){
            Toast.makeText(this, "add alarm button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.add_alarm_view)
            initiateAddAlarmButtons()
        }
    }

    /**
     * Does all initialization necessary when switching to timer view
     */
    private fun initiateTimerView(){
        timer = findViewById(R.id.timer_chronometer) //:( why this needed
        if(!timerRunning){
            timer.base = SystemClock.elapsedRealtime() - timerTimeDifference
        }else{
            timer.base = SystemClock.elapsedRealtime() - lastTimerTime
            timer.start()
        }
        val startButton: Button = findViewById(R.id.start_timer)
        val stopButton: Button = findViewById(R.id.stop_timer)
        val resetButton: Button = findViewById(R.id.reset_timer)
        initiateNavButtons()
        startButton.setOnClickListener(){
            val timeString: String = findViewById<EditText>(R.id.timer_time_input).text.toString()
            var colonCount = 0
            for(letter in timeString){
                if(letter == ':'){
                    colonCount++
                }
            }
            if(colonCount == 2){
                val format: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
                val time: Date = format.parse(timeString)
                if(!timerRunning){
                    timer.base = SystemClock.elapsedRealtime() - timerTimeDifference + time.time - FIVE_HOURS
                    timer.start()
                    timerRunning = true
                }
            }else if(colonCount == 1){
                val format: SimpleDateFormat = SimpleDateFormat("mm:ss")
                val time: Date = format.parse(timeString)
                if(!timerRunning){
                    timer.base = SystemClock.elapsedRealtime() - timerTimeDifference + time.time - FIVE_HOURS
                    timer.start()
                    timerRunning = true
                }
            }else{
                //handle the bad not valid things
                Toast.makeText(this, "Please insert a valid time!!!", Toast.LENGTH_LONG).show()
            }
        }
        stopButton.setOnClickListener(){
            if(timerRunning){
                timerTimeDifference = SystemClock.elapsedRealtime() - timer.base
                timerRunning = false
                timer.stop()
            }
        }
        resetButton.setOnClickListener(){
            timerTimeDifference = 0
            timer.base = SystemClock.elapsedRealtime()
        }
        timer.setOnChronometerTickListener {
            if(timerRunning){
                lastTimerTime = SystemClock.elapsedRealtime() - timer.base
            }
        }
    }

    /**
     * Does all necessary button and task initialization for when switching to stopwatch view
     */
    private fun initiateStopwatch(){
        stopwatch = findViewById(R.id.stopwatch_chronometer) //TODO: delete this later after figuring out how to make chronometer work properly
        if(!stopwatchRunning){
            stopwatch.base = SystemClock.elapsedRealtime() - timeDifference
        }else{
            stopwatch.base = SystemClock.elapsedRealtime() - lastTime
            stopwatch.start()
        } //well for whatever reason the chronometer needs to be reinitialized or it doesn't work and that's a problem
        initiateNavButtons()
        val startButton: Button = findViewById(R.id.start_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val resetButton: Button = findViewById(R.id.reset_button)
        startButton.setOnClickListener(){
            if(!stopwatchRunning){
                stopwatch.base = SystemClock.elapsedRealtime() - timeDifference
                stopwatchRunning = true
                stopwatch.start()
            }
        }
        stopButton.setOnClickListener(){
            if(stopwatchRunning){
                timeDifference = SystemClock.elapsedRealtime() - stopwatch.base
                stopwatchRunning = false
                stopwatch.stop()
            }
        }
        resetButton.setOnClickListener(){
            timeDifference = 0
            lastTime = 0
            stopwatch.base = SystemClock.elapsedRealtime()
        }
        stopwatch.setOnChronometerTickListener {
            if(stopwatchRunning){
                lastTime = SystemClock.elapsedRealtime() - stopwatch.base
            }
        }
    }

    /**
     * Initiates the buttons for add alarm
     */
    private fun initiateAddAlarmButtons(){

    }

}
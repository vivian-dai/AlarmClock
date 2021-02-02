package com.v341196137.alarmclock
// Kotlin libraries

// Android libraries
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Gravity
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_alarm_view.*
import java.util.LinkedList


// do most of our work in here
// main hub for changing layouts and views and stuff
// buttononclick events to change layouts when clicked on
class MainActivity : AppCompatActivity() {
    private var stopwatchRunning:Boolean = false
    private var stopwatchInitiated: Boolean = false
    private var timeDifference: Long = 0
    private var lastTime: Long = 0

    private val hourIDConstant = 600
    private val minuteIDConstant = 750
    private var hourSelected = 70
    private var minuteSelected = 70
    //private lateinit var stopwatch: Chronometer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init textViews for adding alarm clock


        //variables
        var alarmList = LinkedList<AlarmData>()

        initiateAlarmViewButtons()

    }

    /**
     * Hi Sherwin you should javadoc this :P <:( i HATE java docs
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
            initiateTimerViewButtons()
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
            initiateAlarm()
        }
    }

    /**
     * Does all button initialization necessary when switching to timer view
     */
    private fun initiateTimerViewButtons(){
        initiateNavButtons()
    }

    /**
     * Does all necessary button and task initialization for when switching to stopwatch view
     */
    private fun initiateStopwatch(){
        var stopwatch: Chronometer = findViewById(R.id.stopwatch_chronometer) //TODO: delete this later after figuring out how to make chronometer work properly
        if(!stopwatchRunning){
            stopwatch.base = SystemClock.elapsedRealtime() - timeDifference
        }else{
            stopwatch.base = SystemClock.elapsedRealtime() - lastTime
            stopwatch.start()
            stopwatchRunning = true
        }
        initiateNavButtons()
        val startButton: Button = findViewById(R.id.start_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val resetButton: Button = findViewById(R.id.reset_button)
        // you can just replace it with ids instead of this
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
    private fun initiateAlarm(){
        initiateHours()
        initiateMinutes()
        cancel_alarm_button.setOnClickListener(){
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            setContentView(R.layout.activity_main)
            initiateAlarmViewButtons()
        }
        confirm_alarm_button.setOnClickListener(){
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show()
            setContentView(R.layout.activity_main)
            initiateAlarmViewButtons()
        }


    }
    /**
     * Initiates the hours to select for the add alarm view
     */
    private fun initiateHours(){
        for(i in 0..23){
            val hour_add_view = TextView(this)
            hour_add_view.id = i+hourIDConstant
            hour_add_view.textSize = 45f
            hour_add_view.setPadding(0, 50, 0, 50)
            hour_add_view.gravity = Gravity.CENTER
            if (i < 10)
                hour_add_view.text = "0"+i.toString()
            else
                hour_add_view.text = i.toString()
            hour_linear_view.addView(hour_add_view)
            hour_add_view.setOnClickListener(){
                if (hourSelected > 50){
                    hour_add_view.setBackgroundColor(Color.parseColor("#ACCEF7"))
                    hourSelected = i
                } else{
                    (findViewById(hourSelected+hourIDConstant) as TextView).setBackgroundColor(Color.parseColor("#FFFFFF"))
                    hour_add_view.setBackgroundColor(Color.parseColor("#ACCEF7"))
                    hourSelected = i
                }
            }
        }
    }
    /**
     * Initiates the minutes to select for the add alarm view
     */
    private fun initiateMinutes(){
        for(i in 0..59) {
            val minute_add_view = TextView(this)
            minute_add_view.id = i+minuteIDConstant
            minute_add_view.textSize = 45f
            minute_add_view.setPadding(0, 50, 0, 50)
            minute_add_view.gravity = Gravity.CENTER
            if (i < 10)
                minute_add_view.text = "0"+i.toString()
            else
                minute_add_view.text = i.toString()
            minute_linear_view.addView(minute_add_view)
            minute_add_view.setOnClickListener(){
                if (minuteSelected > 65){
                    minute_add_view.setBackgroundColor(Color.parseColor("#ACCEF7"))
                    minuteSelected = i
                } else{
                    (findViewById(minuteSelected+minuteIDConstant) as TextView).setBackgroundColor(Color.parseColor("#FFFFFF"))
                    minute_add_view.setBackgroundColor(Color.parseColor("#ACCEF7"))
                    minuteSelected = i
                }
            }
        }
    }

}
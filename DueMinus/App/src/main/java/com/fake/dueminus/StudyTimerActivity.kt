package com.fake.dueminus

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudyTimerActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var resetButton: Button

    private var timer: CountDownTimer? = null

    private var timeLeft =
        25 * 60 * 1000L

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_study_timer
        )

        timerText =
            findViewById(R.id.tvTimer)

        startButton =
            findViewById(R.id.btnStartTimer)

        resetButton =
            findViewById(R.id.btnResetTimer)

        updateTimerText()

        startButton.setOnClickListener {

            timer?.cancel()

            timer =
                object : CountDownTimer(
                    timeLeft,
                    1000
                ) {

                    override fun onTick(
                        millisUntilFinished: Long
                    ) {

                        timeLeft =
                            millisUntilFinished

                        updateTimerText()
                    }

                    override fun onFinish() {

                        timeLeft = 0

                        updateTimerText()

                        startButton.text =
                            "Start"
                    }
                }.start()

            startButton.text =
                "Running..."
        }

        resetButton.setOnClickListener {

            timer?.cancel()

            timeLeft =
                25 * 60 * 1000L

            updateTimerText()

            startButton.text =
                "Start"
        }
    }

    private fun updateTimerText() {

        val minutes =
            timeLeft / 60000

        val seconds =
            (timeLeft % 60000) / 1000

        timerText.text =
            String.format(
                "%02d:%02d",
                minutes,
                seconds
            )
    }

    override fun onDestroy() {

        super.onDestroy()

        timer?.cancel()
    }
}
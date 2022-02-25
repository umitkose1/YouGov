package com.casestudy.yougov

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.casestudy.yougov.databinding.ActivityMainBinding
import com.casestudy.yougov.adapter.RvAdapter
import com.casestudy.yougov.model.TimerItemsModel
import com.casestudy.yougov.ui.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainActivityMainBinding: ActivityMainBinding
    private lateinit var adapter: RvAdapter
    private lateinit var timer: CountDownTimer
    private var data = ArrayList<TimerItemsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = mainActivityMainBinding!!.root
        adapter = RvAdapter(listOf(), clickListener = {})
        mainActivityMainBinding!!.rvList.adapter = adapter

        mainActivityMainBinding!!.rvList.layoutManager = LinearLayoutManager(applicationContext)
        setContentView(view)

        mainActivityMainBinding.btnStart.setOnClickListener {
            if (data.isNotEmpty()) {
                timer.cancel()
            }
            if (mainActivityMainBinding.etHours.text.isNullOrEmpty() || mainActivityMainBinding.etMinutes.text.isNullOrEmpty() || mainActivityMainBinding.etSeconds.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,"Please enter suitable time value",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            data.add(
                TimerItemsModel(
                    mainActivityMainBinding.etHours.text.toString(),
                    mainActivityMainBinding.etMinutes.text.toString(),
                    mainActivityMainBinding.etSeconds.text.toString()
                )
            )

            var millis = (mainActivityMainBinding.etHours.text.toString()
                .toInt() * (1000 * 60 * 60)).toLong() + (mainActivityMainBinding.etMinutes.text.toString()
                .toInt() * (1000 * 60)).toLong() + (mainActivityMainBinding.etSeconds.text.toString()
                .toInt() * (1000)).toLong()

            timer = object : CountDownTimer(millis, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    val minute = ((millisUntilFinished / (1000 * 60)) % 60)
                    val seconds = (millisUntilFinished / 1000) % 60
                    val hours = ((millisUntilFinished / (1000 * 60 * 60)) % 24)

                    data[0] =
                        TimerItemsModel(hours.toString(), minute.toString(), seconds.toString())
                    adapter = RvAdapter((data), clickListener = {
                        mainActivityMainBinding.etHours.setText(it.hours)
                        mainActivityMainBinding.etMinutes.setText(it.minutes)
                        mainActivityMainBinding.etSeconds.setText(it.seconds)
                    })

                    mainViewModel.initChangeListener()

                }


                override fun onFinish() {

                }
            }
            clearInput()
            timer.start()
        }

        mainViewModel.liveData.observe(this@MainActivity) {
            if (it) {
                adapter.notifyItemChanged(0)

                mainActivityMainBinding.rvList.adapter = adapter
            }
        }
    }

    private fun clearInput() {
        mainActivityMainBinding.etHours.text.clear()
        mainActivityMainBinding.etMinutes.text.clear()
        mainActivityMainBinding.etSeconds.text.clear()

    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }
}

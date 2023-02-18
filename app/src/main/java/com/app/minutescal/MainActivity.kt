package com.app.minutescal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate :TextView? = null
    private var minutesAns :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateButton :Button = findViewById(R.id.datePickerButton)
        tvSelectedDate = findViewById(R.id.tvDateSelector)
        minutesAns = findViewById(R.id.minutes)
        dateButton.setOnClickListener {
            clickOnButton()
        }
    }
    private fun clickOnButton(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "selected $selectedYear/${selectedMonth+1}/$selectedDayOfMonth", Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val thedate = sdf.parse(selectedDate)
                thedate?.let {
                    val selectedDateInMinute = thedate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentdateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentdateInMinutes - selectedDateInMinute
                        minutesAns?.text = differenceInMinutes.toString()
                    }
                }
            } ,
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}
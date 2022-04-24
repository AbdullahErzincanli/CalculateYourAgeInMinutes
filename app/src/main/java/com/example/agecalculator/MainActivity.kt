package com.example.agecalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var txtselectedDate: TextView?=null
    private var txtMinutes: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        txtselectedDate = findViewById(R.id.txtSelectedDate)
        txtMinutes = findViewById(R.id.txtMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }

    }
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDayOfMonth ->
            Toast.makeText(this,"button pressed",Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/${selectedYear}"
            txtselectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time/60000
            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            txtMinutes?.text = differenceInMinutes.toString()
        },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()-8640000
    dpd.show()
    }
}
package com.wgoweb.ageinminutes


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // filename

        btnClickMe.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)  // month count form 0
        val day = myCalendar.get((Calendar.DAY_OF_MONTH))

        var dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, SelectedMonth , SelectedDayOfMonth ->

                val selectedDate = "$SelectedDayOfMonth/${SelectedMonth +1}/$selectedYear"
                selectedDateTextView.text = selectedDate
                // get selected date by minutes
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theBirthdayDate = sdf.parse(selectedDate)
                val selectDateinMinutes = theBirthdayDate!!.time / 60000
                // get current date by minutes
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectDateinMinutes
                // how old in minutes
                ageInMinutesTextView.text = differenceInMinutes.toString()
                // how year
                ageTextView.text = (differenceInMinutes / 1440 ).toString() + " days old"
            }
            , year
            , month
            ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }

}
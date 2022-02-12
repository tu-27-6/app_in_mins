package com.example.app_in_minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //Su dung private de bien nay chi co the ap dung
    //o trong class MainActivity, tranh truong hop
    //app crash
    private var selectedDate: TextView? = null
    private var ageInMins: TextView? = null
    private var showSelectedDate: TextView? = null
    private var showMins: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //chon button theo id
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        //chon textview theo id
        selectedDate = findViewById(R.id.SelectedDate)
        ageInMins = findViewById(R.id.result)
        showSelectedDate = findViewById(R.id.selected_date)
        showMins = findViewById(R.id.minutes)

        //Goi su kien khi click vao nut
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }



    }

    //ham xu ly logic
    private fun clickDatePicker() {
        //chon thoi gian ngay thang nam
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            //Toast se hien ra 1 pop up xuat hien voi thoi gian tuy thuoc vao LENGTH_SHORT or LENGTH_LONG
            Toast.makeText(this, "year was $year, month was ${month + 1}", Toast.LENGTH_SHORT).show()

            //show thoi gian da chon
            val showDate = "$dayOfMonth/${month + 1}/$year"
            selectedDate?.text = showDate

            //chuyen thoi gian tu dang string sang dang chuan
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(showDate)

            theDate?.let {
                //thoi gian tu nam 1970 den thoi diem duoc chon, chia 60000 la miliseconds to minutues
                val dateSelectedInMins = theDate.time / 60000

                //thoi gian tu nam 1970 den hien tai tinh theo phut
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000

                val result = currentDate - dateSelectedInMins

                ageInMins?.text = result.toString()
            }



        },year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()

        //!! can not null
        showMins!!.visibility = VISIBLE
        showSelectedDate!!.visibility = VISIBLE







    }

}
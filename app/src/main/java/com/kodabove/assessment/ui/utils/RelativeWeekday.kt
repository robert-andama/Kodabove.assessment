package com.kodabove.assessment.ui.utils

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.text.format.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.abs

class RelativeWeekday(private val context : Context, private val localDate: String, private val format: String) {

    private val mCalendar: Calendar = Calendar.getInstance()

    override fun toString(): String {
        val date = LocalDateTime.parse(localDate, DateTimeFormatter.ofPattern(format))

        val today = Calendar.getInstance(Locale.getDefault())
        mCalendar.time = localDateTimeToDate(date)

        val dayOfYear = mCalendar[Calendar.DAY_OF_YEAR]
        return if (abs(dayOfYear - today[Calendar.DAY_OF_YEAR]) < 2) {
            getRelativeDay(today)
        } else weekDay
    }

    private fun localDateToDate(localDate: LocalDate): Date {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar[localDate.year, localDate.monthValue - 1] = localDate.dayOfYear
        return calendar.time
    }

    private fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar[
                localDateTime.year,
                localDateTime.monthValue - 1,
                localDateTime.dayOfMonth,
                localDateTime.hour,
                localDateTime.minute
        ] = localDateTime.second
        return calendar.time
    }

    private fun localTimeToDate(localTime: LocalTime): Date {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar[0, 0, 0, localTime.hour, localTime.minute] = localTime.second
        return calendar.time
    }

    private fun getRelativeDay(today: Calendar): String {
        return DateUtils.getRelativeDateTimeString(
            context,
            mCalendar.timeInMillis,
            DateUtils.DAY_IN_MILLIS,
            today.timeInMillis,
            DateUtils.FORMAT_SHOW_WEEKDAY).toString()
    }

    private val weekDay: String
        get() {
            val dayFormat = SimpleDateFormat("d.m.y H:m:s", Locale.getDefault())
            return dayFormat.format(mCalendar.timeInMillis)
        }
}
package com.example.lenovo.footballapps.Utils

import java.text.SimpleDateFormat
import java.util.*

fun toSimpleString(date : Date?) : String = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy").format(date)
}
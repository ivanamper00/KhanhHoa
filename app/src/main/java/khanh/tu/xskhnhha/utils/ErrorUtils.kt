package khanh.tu.xskhnhha.utils

import android.util.Log

fun writeLogs(message: String){
    Log.d("APPLICATION_LOGS", message)
}

fun writeLogs(e: Exception){
    Log.d("APPLICATION_LOGS", """
        Error: ${e.cause?.message}
        Description: ${e.message}
    """.trimIndent())
}
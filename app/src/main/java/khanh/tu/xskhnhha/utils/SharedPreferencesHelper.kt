package khanh.tu.xskhnhha.utils

import android.content.Context
import android.content.ContextWrapper

class SharedPreferencesHelper(context: Context): ContextWrapper(context) {

    private val sharedPreference = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    fun getIsAppRegistered(): Boolean {
        return sharedPreference.getBoolean(APP_INSTALLED, false)
    }

    companion object {
        private const val APP_INSTALLED = "APP_INSTALLED"
        private var INSTANCE: SharedPreferencesHelper? = null
        fun getInstance(context: Context): SharedPreferencesHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SharedPreferencesHelper(context).also {
                    INSTANCE = it
                }
            }
        }
    }
}
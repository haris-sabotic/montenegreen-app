package com.fiveg.montenegreen.util

import android.content.Context

object GlobalData {
    private var _token: String? = null

    fun loadTokenFromSharedPrefs(content: Context) {
        val sharedPrefs = content.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        _token = sharedPrefs.getString("token", null)
    }

    fun saveToken(content: Context, value: String?) {
        _token = value

        val sharedPrefs = content.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        if (value == null) {
            editor.remove("token")
        } else {
            editor.putString("token", value)
        }
        editor.apply()
    }

    fun getToken(): String? {
        return _token
    }

    val CURRENT_USER_POINTS = 35
}
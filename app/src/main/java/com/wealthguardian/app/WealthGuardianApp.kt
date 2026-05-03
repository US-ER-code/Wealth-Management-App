package com.wealthguardian.app

import android.app.Application
import android.content.Context
import com.wealthguardian.app.data.db.AppDatabase

class WealthGuardianApp : Application() {
    val database by lazy { AppDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: WealthGuardianApp
        private const val PREFS_NAME = "wealth_guardian_prefs"
        private const val KEY_FIRST_LAUNCH = "first_launch"

        fun isFirstLaunch(): Boolean {
            val prefs = instance.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getBoolean(KEY_FIRST_LAUNCH, true)
        }

        fun setFirstLaunchDone() {
            val prefs = instance.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
        }
    }
}

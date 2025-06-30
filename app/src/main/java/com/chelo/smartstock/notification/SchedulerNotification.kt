package com.chelo.smartstock.notification

import android.content.Context
import android.icu.util.Calendar
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SchedulerNotification @Inject constructor(@ApplicationContext private val context: Context) {


    fun scheduleNotification() {
        val dayDelay = delay()
        Log.i("CHELO", dayDelay.toString())
        val dailyWorker = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(dayDelay, TimeUnit.MILLISECONDS)
            .build()

        try {
            WorkManager.getInstance(context)
                .enqueueUniqueWork("TestOneTimeNotification", ExistingWorkPolicy.REPLACE, dailyWorker)
        }catch (e: Exception){
            Log.i("CHELO", e.toString())
        }
    }


    private fun delay(): Long {
        val now = Calendar.getInstance()

        val instance = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 18)
            set(Calendar.MINUTE, 53)
            set(Calendar.SECOND, 0)
            if (before(now))
                add(Calendar.DAY_OF_YEAR, 1)

        }
        return instance.timeInMillis - now.timeInMillis
    }
}
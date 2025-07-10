package com.chelo.smartstock.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.chelo.smartstock.MainActivity
import com.chelo.smartstock.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@RequiresApi(Build.VERSION_CODES.O)
@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val scheduler: SchedulerNotification,
    private val checker: NotificationCheckerUseCase,
) : CoroutineWorker(context, workerParams) {

    init {
        Log.i("CHELO", "WorkerInstanciado ")
    }

    companion object {
        const val NOTIFICATION_CHANNEL = "notification_channel"
    }


    override suspend fun doWork(): Result {
        createNotificationChannel()
        try {
            when {
                checker.isProductExpired() ->{
                    showNotification("Alerta!" , "Tienes un producto vencido! ")
                }
                checker.checkExpireDate() -> {
                    showNotification("Atencion", "Tienes uno o mas productos pronto a vencer! ")
                    scheduler.scheduleNotification()
                }


            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private fun showNotification(title: String, message: String) {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(R.drawable.emptyicon)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(0, notification)
    }


    private fun createNotificationChannel() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL, "ChannelNotification",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notificaciones de vencimientos."
        }
        notificationManager.createNotificationChannel(channel)

    }

}
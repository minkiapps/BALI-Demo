package com.minkiapps.balidemo

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import com.minkiapps.balidemo.App.Companion.NOTIFICATION_CHANNEL_ID
import com.minkiapps.balidemo.domain.*
import com.minkiapps.balidemo.util.isHwFoldAbleDevice

class BaliDemoService : Service() {

    private val gson = GsonBuilder().create()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            Actions.TEMPLATE0.name -> {
                val bundle = createBundle(createTemplateZero(), TemplateIndex.ZERO, R.drawable.ic_ahead)
                startForeground(1, createNotification(bundle,
                    "Navigation", "Showing you in which direction you should go next on the cover screen."))
            }
            Actions.TEMPLATE1.name -> {
                val bundle = createBundle(createTemplateOne(), TemplateIndex.ONE, R.drawable.taxi)
                startForeground(2, createNotification(bundle,
                    "Taxi Order", "Showing you the status of the taxi."))
            }
            Actions.TEMPLATE2.name -> {
                val bundle = createBundle(createTemplateTwo(), TemplateIndex.TWO, R.drawable.step)
                startForeground(3, createNotification(bundle,
                    "Steps Counter",
                    "Daily steps and meters are count and displayed on the cover screen."))
            }
            Actions.STOP.name -> stopService()
        }
        return START_STICKY
    }

    private fun createBundle(templateDemo: TemplateDemo,
                             templateIndex: TemplateIndex,
                             iconRes : Int): Bundle {
        val bundle = Bundle()
        if(isHwFoldAbleDevice()) {
            return bundle
        }

        val resJson = gson.toJson(templateDemo)
        bundle.putString("res_id", resJson)
        bundle.putInt("notification_index", templateIndex.ordinal)
        bundle.putParcelable("extra_icon", Icon.createWithResource(this, iconRes))
        return bundle
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun stopService() {
        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(bundle: Bundle,
                                   notificationTitle : String,
                                   notificationContent : String): Notification {

        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            this,
            NOTIFICATION_CHANNEL_ID
        ) else Notification.Builder(this)

        return builder
            .setExtras(bundle)
            .setContentTitle(notificationTitle)
            .setContentText(notificationContent)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true) //https://stackoverflow.com/a/53119192/13782111
            .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
            .build()
    }

    private fun createTemplateZero() : TemplateZero {
        return TemplateZero(
            templateContent = "Arrive at Cloud Park, Longgang District, Shenzhen City, Guangdong Province",
            contentColor = 0x000000,
            disColor = -0xFFFF01,
            disColorContent = "Arrive"
        )
    }

    private fun createTemplateOne() : TemplateOne {
        return TemplateOne(
            templateTitle = "Coffee Latte",
            titleColor = 0xFFFC3E1B.toInt(),
            templateContent = "Is in the making",
            templateSecondly = "Approx. waiting time: 2min"
        )
    }

    private fun createTemplateTwo() : TemplateTwo {
        return TemplateTwo(
            templateTitle = "Steps",
            progress = 66,
            midContent = "1.315",
            midContentBigSize = true,
            bottomContent = "934m",
            progressColor = ContextCompat.getColor(this, R.color.progress_color),
            progressBgColor = ContextCompat.getColor(this, R.color.progress_bg_color)
        )
    }
}

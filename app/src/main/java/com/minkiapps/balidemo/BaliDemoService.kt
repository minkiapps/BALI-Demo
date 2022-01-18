package com.minkiapps.balidemo

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import com.minkiapps.balidemo.App.Companion.NOTIFICATION_CHANNEL_ID
import com.minkiapps.balidemo.domain.*
import android.app.NotificationManager
import android.content.Context
import com.huawei.android.fsm.*
import com.huawei.android.fsm.HwFoldScreenManagerEx.FoldableStateListener
import timber.log.Timber

class BaliDemoService : Service() {

    private val gson = GsonBuilder().create()

    private val notificationManager : NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private var foldableStateListener : FoldableStateListener? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            Actions.TEMPLATE0.name -> {
                val bundle = createBundle(createTemplateZero(), TemplateIndex.ZERO, R.drawable.ic_ahead)
                val notification = createNotification(
                    bundle,
                    "Navigation",
                    "Showing you in which direction you should go next on the cover screen."
                )
                startForeground(NOTIFICATION_ID, notification)
                registerFoldableListener(notification)
            }
            Actions.TEMPLATE1.name -> {
                val bundle = createBundle(createTemplateOne(), TemplateIndex.ONE, R.drawable.taxi)
                val notification = createNotification(
                    bundle,
                    "Taxi Order", "Showing you the status of the taxi."
                )
                startForeground(NOTIFICATION_ID, notification)
                registerFoldableListener(notification)
            }
            Actions.TEMPLATE2.name -> {
                val bundle = createBundle(createTemplateTwo(), TemplateIndex.TWO, R.drawable.step)
                val notification = createNotification(
                    bundle,
                    "Steps Counter",
                    "Daily steps and meters are count and displayed on the cover screen."
                )
                startForeground(NOTIFICATION_ID, notification)
                registerFoldableListener(notification)
            }
            Actions.STOP.name -> stopService()
        }
        return START_STICKY
    }

    private fun createBundle(templateDemo: TemplateDemo,
                             templateIndex: TemplateIndex,
                             iconRes : Int): Bundle {
        val bundle = Bundle()
        if(!isHwFoldAbleDevice()) {
            return bundle
        }

        val resJson = gson.toJson(templateDemo)
        Timber.d("Json for bundle: $resJson")
        bundle.putString("res_id", resJson)
        bundle.putInt("notification_index", templateIndex.ordinal)
        bundle.putParcelable("extra_icon", Icon.createWithResource(this, iconRes))
        return bundle
    }

    private fun registerFoldableListener(notification : Notification) {
        if(!isHwFoldAbleDevice())
            return

        foldableStateListener?.let {
            unregisterFoldableState(it)
        }

        val listener = object : FoldableStateListener {
            override fun onStateChange(bundle: Bundle?) {
                val foldableState : Int = bundle?.getInt(EXTRA_FOLD_STATE, 0) ?: 0

                Timber.d("Foldable state changed: $foldableState")

                if(foldableState == FOLD_STATE_FOLDED) {
                    notificationManager.notify(NOTIFICATION_ID, notification)
                }
            }
        }
        registerFoldStateListener(listener)

        this.foldableStateListener = listener
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun stopService() {
        foldableStateListener?.let {
            unregisterFoldableState(it)
        }
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
            templateTitle = "Driver on the way",
            titleColor = 0xFFFC3E1B.toInt(),
            templateContentFirst = TemplateContent(
                oriContent = "BD51-SMR",
                oriContentColor = Color.WHITE
            ),
            templateContentSecondly = TemplateContent(
                oriContent = "Audi A3 - White",
                oriContentColor = ContextCompat.getColor(this, R.color.grey),
            ),
            templateBottom = TemplateContent(
                oriContent = "Distance: 2km, 2min",
                oriContentColor = ContextCompat.getColor(this, R.color.grey),
                disContents = listOf(
                    DisContent(
                        disContent = "2km",
                        disContentColor = Color.WHITE
                    ),
                    DisContent(
                        disContent = "2min",
                        disContentColor = Color.WHITE
                    )
                )
            )
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

    companion object {
        private const val NOTIFICATION_ID = 1
    }
}

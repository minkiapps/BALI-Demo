package com.minkiapps.balidemo.util

import android.content.Context
import android.os.Build

fun Context.isHwFoldAbleDevice(): Boolean {
    val device: String = Build.DEVICE
    if (device == "HWTAH" || device == "HWTAH-C" || device == "unknownRLI" || device == "unknownTXL" || device == "unknownRHA" || device == "HWTET") {
        return true
    }
    if (device == "HWBAL" || device == "unknownQTZ") {
        return true
    }
    return applicationContext.packageManager.hasSystemFeature("com.huawei.hardware.sensor.posture")
}
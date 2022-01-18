package com.huawei.android.fsm

import com.huawei.android.fsm.HwFoldScreenManagerEx.FoldDisplayModeListener
import com.huawei.android.fsm.HwFoldScreenManagerEx.FoldableStateListener
import timber.log.Timber

private const val TAG = "HWFold"

fun getDisplayMode() : Int {
    try {
        val cl = Class.forName("com.huawei.android.fsm.HwFoldScreenManagerEx")
        val method = cl.getDeclaredMethod("getDisplayMode")
        return method.invoke(null) as Int
    } catch (e: Exception) {
        Timber.i(TAG, "Failed to fetch displayMode, ${e.message}", e)
    }
    return 0
}

fun getFoldableState() : Int {
    try {
        val cl = Class.forName("com.huawei.android.fsm.HwFoldScreenManagerEx")
        val method = cl.getDeclaredMethod("getFoldableState")
        return method.invoke(null) as Int
    } catch (e: Exception) {
        Timber.i(TAG, "Failed to fetch foldable state, ${e.message}", e)
    }
    return 0
}

fun registerFoldStateListener(foldListener: FoldableStateListener) {
    try {
        val cl = Class.forName("com.huawei.android.fsm.HwFoldScreenManagerEx")
        val method = cl.getDeclaredMethod(
            "registerFoldableState",
            FoldableStateListener::class.java,
            Int::class.javaPrimitiveType
        )
        method.invoke(null, foldListener, 1)
    } catch (e: Exception) {
        Timber.i(TAG, "Failed to register foldable state listener, ${e.message}", e)
    }
}

fun unregisterFoldableState(foldListener: FoldableStateListener) {
    try {
        val cl = Class.forName("com.huawei.android.fsm.HwFoldScreenManagerEx")
        val method =
            cl.getDeclaredMethod("unregisterFoldableState", FoldableStateListener::class.java)
        method.invoke(null, foldListener)
    } catch (e: Exception) {
        Timber.i(TAG, "Failed to unregister foldable state listener, ${e.message}", e)
    }
}

fun registerFoldDisplayMode(displayListener: FoldDisplayModeListener) {
    try {
        val cl = Class.forName("com.huawei.android.fsm.HwFoldScreenManagerEx")
        val method =
            cl.getDeclaredMethod("registerFoldDisplayMode", FoldDisplayModeListener::class.java)
        method.invoke(null, displayListener)
    } catch (e: Exception) {
        Timber.i(TAG, "Failed to register fold display mode listener, ${e.message}", e)
    }
}

fun unregisterFoldDisplayMode(displayListener: FoldDisplayModeListener) {
    try {
        val cl = Class.forName("com.huawei.android.fsm.HwFoldScreenManagerEx")
        val method = cl.getDeclaredMethod(
            "unregisterFoldDisplayMode",
            FoldDisplayModeListener::class.java
        )
        method.invoke(null, displayListener)
    } catch (e: Exception) {
        Timber.i(TAG, "Failed to unregister fold display mode listener, ${e.message}", e)
    }
}
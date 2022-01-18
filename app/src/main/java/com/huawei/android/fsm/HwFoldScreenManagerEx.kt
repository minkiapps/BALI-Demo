package com.huawei.android.fsm

import android.os.Bundle

class HwFoldScreenManagerEx {

    companion object {
        const val EXTRA_FOLD_STATE = "fold_state"

        const val FOLD_STATE_UNKNOWN = 0
        const val FOLD_STATE_EXPANDED = 1
        const val FOLD_STATE_FOLDED = 2
        const val FOLD_STATE_HALF_FOLDED = 3
    }

    /**
     * val foldState: Int = bundle.getInt("fold_state", 0)
     *
     * 0 unknown
     * 1 expanded
     * 2 folded
     * 3 half folded
     */
    interface FoldableStateListener {
        fun onStateChange(bundle: Bundle?)
    }

    /**
     * display mode can be
     *
     * 0 unknown
     * 2 main display
     * 3 secondary display
     * 4 co display
     */
    interface FoldDisplayModeListener {
        fun onScreenDisplayModeChange(displayMode: Int)
    }
}
package org.openedx.foundation.extension

import androidx.fragment.app.Fragment
import androidx.window.layout.WindowMetricsCalculator
import org.openedx.foundation.presentation.WindowSize
import org.openedx.foundation.presentation.WindowType

fun Fragment.computeWindowSizeClasses(): WindowSize {
    val metrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(requireActivity())

    val widthDp = metrics.bounds.width() /
            resources.displayMetrics.density
    val widthWindowSize = when {
        widthDp < 600f -> WindowType.Compact
        widthDp < 840f -> WindowType.Medium
        else -> WindowType.Expanded
    }

    val heightDp = metrics.bounds.height() /
            resources.displayMetrics.density
    val heightWindowSize = when {
        heightDp < 480f -> WindowType.Compact
        heightDp < 900f -> WindowType.Medium
        else -> WindowType.Expanded
    }
    return WindowSize(widthWindowSize, heightWindowSize)
}

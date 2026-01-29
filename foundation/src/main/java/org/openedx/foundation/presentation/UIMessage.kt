package org.openedx.foundation.presentation

import androidx.compose.material3.SnackbarDuration

open class UIMessage(
    open val message: String = ""
) {
    class SnackBarMessage(
        override val message: String,
        val duration: SnackbarDuration = SnackbarDuration.Long,
    ) : UIMessage(message)

    class ToastMessage(override val message: String) : UIMessage(message)
}

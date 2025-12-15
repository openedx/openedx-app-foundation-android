package org.openedx.foundation.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.withTimeoutOrNull
import org.openedx.foundation.R
import org.openedx.foundation.extension.isInternetError
import org.openedx.foundation.system.ResourceManager

open class BaseViewModel(
    private val resourceManager: ResourceManager,
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiMessage: MutableSharedFlow<UIMessage> = MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 1,
    )
    val uiMessage: SharedFlow<UIMessage> = _uiMessage.asSharedFlow()

    protected fun resolveErrorMessage(
        throwable: Throwable?,
        @StringRes noInternetErrorRes: Int = R.string.foundation_error_no_connection,
        @StringRes defaultErrorRes: Int = R.string.foundation_error_unknown_error,
    ): String {
        val messageRes =
            if (throwable?.isInternetError() == true) noInternetErrorRes else defaultErrorRes
        return resourceManager.getString(messageRes)
    }

    protected suspend fun handleErrorUiMessage(
        throwable: Throwable?,
        @StringRes noInternetErrorRes: Int = R.string.foundation_error_no_connection,
        @StringRes defaultErrorRes: Int = R.string.foundation_error_unknown_error,
    ) {
        _uiMessage.emit(
            UIMessage.SnackBarMessage(
                resolveErrorMessage(
                    throwable = throwable,
                    noInternetErrorRes = noInternetErrorRes,
                    defaultErrorRes = defaultErrorRes,
                )
            )
        )
    }

    protected suspend fun sendMessage(uiMessage: UIMessage) {
        _uiMessage.emit(uiMessage)
    }
}

@Suppress("MagicNumber")
fun TestScope.captureUiMessage(viewModel: BaseViewModel) = async {
    withTimeoutOrNull(5_000) { viewModel.uiMessage.first() }
}

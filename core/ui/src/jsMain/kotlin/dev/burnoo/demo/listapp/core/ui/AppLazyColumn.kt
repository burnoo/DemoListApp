package dev.burnoo.demo.listapp.core.ui

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import kotlinx.coroutines.launch
import org.jetbrains.skiko.SkikoPointerEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun AppLazyColumn(
    listState: LazyListState,
    modifier: Modifier,
    content: LazyListScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        modifier = modifier.then(
            Modifier.onPointerEvent(PointerEventType.Scroll) {
                scope.launch {
                    listState.scrollBy((it.nativeEvent as SkikoPointerEvent).deltaY.toFloat())
                }
            },
        ),
        content = content,
        userScrollEnabled = false,
    )
}

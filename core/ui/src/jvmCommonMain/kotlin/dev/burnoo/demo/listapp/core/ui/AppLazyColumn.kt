package dev.burnoo.demo.listapp.core.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun AppLazyColumn(
    listState: LazyListState,
    modifier: Modifier,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        content = content,
    )
}

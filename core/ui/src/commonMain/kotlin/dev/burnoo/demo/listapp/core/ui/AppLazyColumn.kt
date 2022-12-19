package dev.burnoo.demo.listapp.core.ui

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun AppLazyColumn(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
)

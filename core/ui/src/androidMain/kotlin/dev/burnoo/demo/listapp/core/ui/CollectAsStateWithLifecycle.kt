package dev.burnoo.demo.listapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle as androidxCollectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
actual fun <T> StateFlow<T>.collectAsStateWithLifecycle(): State<T> =
    androidxCollectAsStateWithLifecycle()

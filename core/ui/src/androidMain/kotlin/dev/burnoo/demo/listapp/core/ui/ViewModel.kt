package dev.burnoo.demo.listapp.core.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel as AndroidXViewModel
import androidx.lifecycle.viewModelScope as androidXViewModelScope
import dev.burnoo.cokoin.viewmodel.getViewModel as getViewModelCokoin
import org.koin.androidx.viewmodel.dsl.viewModel as viewModelKoin
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

actual abstract class ViewModel : AndroidXViewModel() {

    actual val viewModelScope = androidXViewModelScope

    actual override fun onCleared() = Unit
}

@Composable
actual inline fun <reified VM : ViewModel> getViewModel(
    noinline parameters: ParametersDefinition?,
) = getViewModelCokoin<VM>(parameters = parameters)

actual inline fun <reified VM : ViewModel> Module.viewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<VM>,
) = viewModelKoin(qualifier, definition)

package dev.burnoo.demo.listapp.core.ui

import androidx.compose.runtime.Composable
import dev.burnoo.cokoin.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

actual abstract class ViewModel {

    actual val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    protected actual fun onCleared() = Unit

    fun clear() {
        viewModelScope.cancel()
    }
}

@Composable
actual inline fun <reified VM : ViewModel> getViewModel(
    noinline parameters: ParametersDefinition?,
): VM = get()

actual inline fun <reified VM : ViewModel> Module.viewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<VM>,
) = factory(qualifier, definition)

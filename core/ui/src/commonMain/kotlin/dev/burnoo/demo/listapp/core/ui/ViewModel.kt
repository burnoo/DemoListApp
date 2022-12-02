package dev.burnoo.demo.listapp.core.ui

import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import org.koin.core.definition.Definition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

expect abstract class ViewModel {

    val viewModelScope: CoroutineScope

    protected fun onCleared()
}

@Composable
expect inline fun <reified VM : ViewModel> getViewModel(
    noinline parameters: ParametersDefinition? = null,
): VM

expect inline fun <reified VM : ViewModel> Module.viewModel(
    qualifier: Qualifier? = null,
    noinline definition: Definition<VM>,
): Pair<Module, InstanceFactory<VM>>

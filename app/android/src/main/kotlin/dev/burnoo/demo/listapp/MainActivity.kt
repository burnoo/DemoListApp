package dev.burnoo.demo.listapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import dev.burnoo.demo.listapp.composable.routing.AppRouter
import dev.burnoo.demo.listapp.core.designsystem.theme.AppTheme
import dev.burnoo.demo.listapp.di.WithDependencyInjection

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WithDependencyInjection {
                App()
            }
        }
    }
}

@Composable
internal fun App() {
    AppTheme {
        AppRouter()
    }
}

package dev.burnoo.demo.listapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.burnoo.demo.listapp.composable.AppRouter
import dev.burnoo.demo.listapp.core.designsystem.AppTheme
import dev.burnoo.demo.listapp.di.WithDependencyInjection

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WithDependencyInjection {
                AppTheme {
                    AppRouter()
                }
            }
        }
    }
}

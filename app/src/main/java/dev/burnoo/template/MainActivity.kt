package dev.burnoo.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.burnoo.template.composable.AppRouter
import dev.burnoo.template.core.designsystem.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppRouter()
            }
        }
    }
}

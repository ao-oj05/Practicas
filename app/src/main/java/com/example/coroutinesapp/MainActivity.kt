package com.luisperez.coroutinesappa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.luisperez.coroutinesappa.navManager.NavManager
import com.luisperez.coroutinesappa.ui.theme.CoroutinesAppATheme
import com.luisperez.coroutinesappa.viewModels.CoroutinesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CoroutinesViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            CoroutinesAppATheme {
                NavManager(viewModel)
            }
        }
    }
}

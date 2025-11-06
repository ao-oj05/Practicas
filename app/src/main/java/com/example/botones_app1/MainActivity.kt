package com.luisperez.segundointento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.luisperez.segundointento.presentations.navManager.NavManager
import com.luisperez.segundointento.ui.theme.SegundoIntentoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            Text("Hola mundo desde Suchiapa york")
            SegundoIntentoTheme {
                NavManager()
            }
        }
    }
}

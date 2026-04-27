package com.pdm0126.orderupp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pdm0126.orderupp1.ui.theme.OrderUpP1Theme
import navegation.BasicNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrderUpP1Theme {
                BasicNavigation()
                }
            }
        }
    }



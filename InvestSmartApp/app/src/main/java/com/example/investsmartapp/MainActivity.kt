@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.investsmartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.investsmartapp.ui.InvestSmartAppTheme
import com.example.investsmartapp.ui.screens.InvestSmartApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InvestSmartAppTheme {
                InvestSmartApp()
            }
        }
    }
}

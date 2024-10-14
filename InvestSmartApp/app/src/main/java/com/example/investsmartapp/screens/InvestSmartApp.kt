package com.example.investsmartapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.investsmartapp.repository.BinanceRepository
import com.example.investsmartapp.ui.screens.InvestmentScreen
import kotlinx.coroutines.delay

@Composable
fun InvestSmartApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("binance") { InvestmentScreen("Binance", "BTCUSDT") }
        composable("vndirect") { InvestmentScreen("VNDIRECT", "VNINDEX") }
        composable("vpbank") { InvestmentScreen("VPBank Securities", "VPBANK") }
    }
}

package com.example.investsmartapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            InvestmentCard(title = "Binance") { navController.navigate("binance") }
            Spacer(modifier = Modifier.height(16.dp))
            InvestmentCard(title = "VNDIRECT") { navController.navigate("vndirect") }
            Spacer(modifier = Modifier.height(16.dp))
            InvestmentCard(title = "VPBank Securities") { navController.navigate("vpbank") }
        }
    }
}

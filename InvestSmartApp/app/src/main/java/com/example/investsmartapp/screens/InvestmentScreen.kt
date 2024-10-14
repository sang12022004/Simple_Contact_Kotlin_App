package com.example.investsmartapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.investsmartapp.repository.BinanceRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestmentScreen(title: String, symbol: String) {
    val repository = BinanceRepository()
    val currentPrice = remember { mutableStateOf("Loading...") }
    val candlestickData = remember { mutableStateOf<List<List<Any>>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Lấy giá hiện tại
        val response = repository.getCurrentPrice(symbol)
        if (response != null && response.isSuccessful) {
            currentPrice.value = response.body()?.price ?: "Unavailable"
        } else {
            currentPrice.value = "Error fetching price"
        }

        // Lấy dữ liệu nến
        val candleResponse = repository.getCandlestickData(symbol, "1d", 30)
        if (candleResponse != null && candleResponse.isSuccessful) {
            candlestickData.value = candleResponse.body() ?: emptyList()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) },
        content = {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = "$title Overview", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Current Price: $${currentPrice.value}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                CandlestickChart(symbol = symbol, candlestickData = candlestickData.value)
            }
        }
    )
}

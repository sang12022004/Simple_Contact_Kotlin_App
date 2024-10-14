package com.example.investsmartapp.repository

import com.example.investsmartapp.network.BinanceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class BinanceRepository {
    private val binanceApi: BinanceApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.binance.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        binanceApi = retrofit.create(BinanceApi::class.java)
    }

    // Hàm để lấy giá hiện tại của một tài sản (ví dụ: BTCUSDT)
    fun getCurrentPrice(symbol: String): Response<com.example.investsmartapp.network.PriceResponse>? {
        return binanceApi.getCurrentPrice(symbol).execute()
    }

    // Hàm để lấy dữ liệu nến cho biểu đồ
    fun getCandlestickData(symbol: String, interval: String, limit: Int): Response<List<List<Any>>>? {
        return binanceApi.getCandlestickData(symbol, interval, limit).execute()
    }
}

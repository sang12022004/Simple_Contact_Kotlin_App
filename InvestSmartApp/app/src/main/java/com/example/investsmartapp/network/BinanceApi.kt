package com.example.investsmartapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApi {
    @GET("api/v3/ticker/price")
    fun getCurrentPrice(@Query("symbol") symbol: String): Call<PriceResponse>

    @GET("api/v3/klines")
    fun getCandlestickData(
        @Query("symbol") symbol: String,
        @Query("interval") interval: String,
        @Query("limit") limit: Int
    ): Call<List<List<Any>>>
}

data class PriceResponse(
    val symbol: String,
    val price: String
)

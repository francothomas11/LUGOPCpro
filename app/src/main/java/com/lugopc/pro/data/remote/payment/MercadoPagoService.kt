package com.lugopc.pro.data.remote.payment

import retrofit2.http.*

interface MercadoPagoService {
    @POST("checkout/preferences")
    suspend fun createPreference(
        @Header("Authorization") authorization: String,
        @Body preference: MercadoPagoPreference
    ): MercadoPagoPaymentResponse
    
    @GET("payments/{id}")
    suspend fun getPaymentStatus(
        @Header("Authorization") authorization: String,
        @Path("id") paymentId: String
    ): MercadoPagoPayment
}

data class MercadoPagoPayment(
    val id: Long,
    val status: String,
    val status_detail: String,
    val description: String,
    val transaction_amount: Double,
    val installments: Int
)

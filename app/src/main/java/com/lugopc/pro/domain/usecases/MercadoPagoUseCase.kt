package com.lugopc.pro.domain.usecases

import com.lugopc.pro.data.remote.payment.MercadoPagoService
import com.lugopc.pro.data.remote.payment.MercadoPagoPreference
import com.lugopc.pro.data.remote.payment.MercadoPagoItem
import com.lugopc.pro.data.remote.payment.MercadoPagoPayer
import com.lugopc.pro.data.remote.payment.MercadoPagoBackUrls
import javax.inject.Inject

class MercadoPagoUseCase @Inject constructor(
    private val mercadoPagoService: MercadoPagoService
) {
    suspend fun createPaymentPreference(
        amount: Double,
        description: String,
        clientEmail: String,
        clientName: String,
        clientPhone: String,
        orderId: String
    ): Result<String> = try {
        val preference = MercadoPagoPreference(
            items = listOf(
                MercadoPagoItem(
                    title = description,
                    quantity = 1,
                    unit_price = amount
                )
            ),
            payer = MercadoPagoPayer(
                email = clientEmail,
                name = clientName,
                phone = com.lugopc.pro.data.remote.payment.MercadoPagoPhone(
                    number = clientPhone
                )
            ),
            back_urls = MercadoPagoBackUrls(
                success = "https://lugopc.com/success",
                failure = "https://lugopc.com/failure",
                pending = "https://lugopc.com/pending"
            ),
            external_reference = orderId
        )
        
        val token = "Bearer YOUR_MERCADO_PAGO_ACCESS_TOKEN"
        val response = mercadoPagoService.createPreference(token, preference)
        Result.success(response.init_point)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    suspend fun checkPaymentStatus(
        paymentId: String
    ): Result<String> = try {
        val token = "Bearer YOUR_MERCADO_PAGO_ACCESS_TOKEN"
        val payment = mercadoPagoService.getPaymentStatus(token, paymentId)
        Result.success(payment.status)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

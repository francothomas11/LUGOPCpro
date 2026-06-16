package com.lugopc.pro.data.remote.payment

import com.google.gson.annotations.SerializedName

data class MercadoPagoPreference(
    val items: List<MercadoPagoItem>,
    val payer: MercadoPagoPayer,
    val back_urls: MercadoPagoBackUrls,
    val auto_return: String = "approved",
    val external_reference: String = "",
    val notification_url: String = ""
)

data class MercadoPagoItem(
    val title: String,
    val quantity: Int,
    val unit_price: Double
)

data class MercadoPagoPayer(
    val email: String,
    val name: String = "",
    val surname: String = "",
    val phone: MercadoPagoPhone = MercadoPagoPhone()
)

data class MercadoPagoPhone(
    val area_code: String = "",
    val number: String = ""
)

data class MercadoPagoBackUrls(
    val success: String = "",
    val failure: String = "",
    val pending: String = ""
)

data class MercadoPagoPaymentResponse(
    @SerializedName("id")
    val preferenceId: String,
    val sandbox_init_point: String,
    val init_point: String,
    val created_at: String
)

package com.chelo.smartstock.data.api

import com.google.gson.annotations.SerializedName

data class ProductDataResponse(
    @SerializedName("product") val product:DataProduct?
)

data class DataProduct(
    @SerializedName("product_name") val nameProduct : String,
    @SerializedName("image_front_url") val imageProduct : String
)

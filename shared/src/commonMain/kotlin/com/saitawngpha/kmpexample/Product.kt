package com.saitawngpha.kmpexample

import kotlinx.serialization.Serializable

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
)

@Serializable
data class Products(
    val items: List<Product>
)
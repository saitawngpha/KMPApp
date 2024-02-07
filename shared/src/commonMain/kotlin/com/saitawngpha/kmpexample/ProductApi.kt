package com.saitawngpha.kmpexample

import co.touchlab.kermit.Logger
import com.saitawngpha.kmpexample.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */
class ProductApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true 
                isLenient = true
                 ignoreUnknownKeys = true
            })
        }
    }

    fun fetchProducts(limit: Int) : Flow<RequestState> {
        return  flow {
            emit(RequestState.Loading)
            delay(2000)
            try {
                emit(
                RequestState.Success(
                    data = Products(
                        items = httpClient.get(urlString = "${BASE_URL}products?limit=$limit").body()
                    )
                )
                )
            }catch (e: Exception){
                Logger.setTag("ProductApi")
                Logger.e{e.message.toString()}
                emit(RequestState.Error(message = "Error while fetching the data."))
            }
        }
    }
}
package com.talmir.sip.task.githubpublicrepositories.network.request

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        println(
            "Sending request %s on %s%n%s".format(
                request.url(), chain.connection(), request.headers()
            )
        )

        return try {
            val response = chain.proceed(request)
            val t2 = System.nanoTime()
            println(
                "Received response for %s in %.1fms%n%s".format(
                    response.request().url(), (t2 - t1) / 1e6, response.headers()
                )
            )
            response
        } catch (e: Exception) {
            val rBuilder = Response.Builder()
                .code(0)
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .header("headerName", request.headers().toString())
                .body(ResponseBody.create(null, "no_content"))
            rBuilder.build()
        }
    }
}

private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(LoggingInterceptor())
    .addNetworkInterceptor(LoggingInterceptor())
    .build()

/**
 * Use the retrofit builder to build a retrofit object using a gson
 * converter with our gsonConverterFactory object pointing to the desired URL
 */
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/search/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

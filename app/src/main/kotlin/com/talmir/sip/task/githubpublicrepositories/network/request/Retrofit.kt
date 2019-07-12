package com.talmir.sip.task.githubpublicrepositories.network.request

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        println(
            "Sending request %s on %s%n%s".format(
            request.url(), chain.connection(), request.headers())
        )

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        println(
            "Received response for %s in %.1fms%n%s".format(
            response.request().url(), (t2 - t1) / 1e6, response.headers())
        )

        return response
    }
}

private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(LoggingInterceptor())
    .addNetworkInterceptor(LoggingInterceptor())
    .build()


// Build the Moshi object that Retrofit will be using, making
// sure to add the Kotlin adapter for full Kotlin compatibility.
private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

/**
 * Use the retrofit builder to build a retrofit object using a moshi
 * converter with our [moshiConverterFactory] object pointing to the desired URL
 */
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/search/repositories/")
    .client(okHttpClient)
    .addConverterFactory(gsonConverterFactory)
    .build()

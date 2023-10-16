package com.project.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class TokenInterceptor : Interceptor {

    val publicKey = "c17ac7eeb431c805b153719370e059af"
    val privateKey = "57be0202fc38a04bf1ff2cc0d84a10ecb46d36c4"

    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateMD5Hash("$timestamp$privateKey$publicKey")

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    private fun generateMD5Hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}

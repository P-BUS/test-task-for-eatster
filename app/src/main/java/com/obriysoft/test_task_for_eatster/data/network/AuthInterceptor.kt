package com.obriysoft.test_task_for_eatster.data.network

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val credentials = "123456:123456"
        val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        val request = original.newBuilder()
            .header("Authorization", "Basic $encodedCredentials")
            .build()

        return chain.proceed(request)
    }
}

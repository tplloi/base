package vn.loitp.up.a.api.coroutine.sv

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.loitp.core.ext.e
import com.loitp.restApi.DateTypeDeserializer
import com.loitp.restApi.restClient.RestRequestInterceptor
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Logger
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object TestApiClient {

    private fun getBaseUrl() = ApiConfiguration.BASE_URL
    private var restRequestInterceptor: RestRequestInterceptor? = null

    private fun getClient(url: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
        return getRetrofit(url, okHttpClient)
    }

    private fun getRetrofit(
        url: String,
        builder: OkHttpClient.Builder
    ): Retrofit {

        restRequestInterceptor = RestRequestInterceptor()

        builder.apply {
            connectTimeout(ApiConfiguration.TIME_OUT, TimeUnit.SECONDS)
            readTimeout(ApiConfiguration.TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(ApiConfiguration.TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(CurlInterceptor(object : Logger {
                    override fun log(message: String) {
                        e("Ok2Curl", message)
                    }
                }))
            restRequestInterceptor?.let { rri ->
                addInterceptor(rri)
            }
            // retryOnConnectionFailure(false)
        }

        // val moshi = Moshi.Builder()
        //        .build()

        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeDeserializer())
            .create()

        return Retrofit.Builder().apply {
            baseUrl(url)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            // addConverterFactory(MoshiConverterFactory.create(moshi))
            addConverterFactory(GsonConverterFactory.create(gson))
            client(builder.build())
        }.build()
    }

    val apiService: ApiService = getClient(getBaseUrl()).create(ApiService::class.java)

    @Suppress("unused")
    fun addHeader(
        name: String,
        value: String
    ) {
        restRequestInterceptor?.addHeader(name, value)
    }

    @Suppress("unused")
    fun addAuthorization(token: String) {
        addHeader(ApiConfiguration.AUTHORIZATION_HEADER, token)
    }

    @Suppress("unused")
    fun removeAuthorization() {
        removeHeader(ApiConfiguration.AUTHORIZATION_HEADER)
    }

    @Suppress("unused")
    fun removeHeader(name: String) {
        restRequestInterceptor?.removeHeader(name)
    }

    @Suppress("unused")
    fun hasHeader(name: String): Boolean {
        val hasHeader = restRequestInterceptor?.hasHeader(name)
        return hasHeader == true
    }
}

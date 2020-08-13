package com.example.puzzleherexamenandroid.data.network

import com.example.puzzleherexamenandroid.model.Puzzle
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private const val BASE_URL = "https://puzzleapiandroid20200809163632.azurewebsites.net"



//private const val BASE_URL = "https://10.0.2.2:5001/"
private val moshi = Moshi.Builder().
add(KotlinJsonAdapterFactory())
    .build()


interface PuzzleApiService{
    @GET("/api/Puzzle")
    suspend fun getPuzzles(): List<Puzzle>
}

object Network {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        //.addConverterFactory(ScalarsConverterFactory.create())
        .client(unSafeOkHttpClient().build())
        .build()

    val puzzle = retrofit.create(PuzzleApiService::class.java)
}

fun unSafeOkHttpClient() : OkHttpClient.Builder {
    val okHttpClient = OkHttpClient.Builder()
    try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?){}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate>  = arrayOf()
        })

        // Install the all-trusting trust manager
        val  sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
            okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts.first() as X509TrustManager)
            okHttpClient.hostnameVerifier { _, _ -> true }
        }
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        // add your other interceptors …
        // add logging as last interceptor
        okHttpClient.addInterceptor(logging)  // <-- this is the important line!
        okHttpClient.connectTimeout(1000, TimeUnit.SECONDS);
        okHttpClient.readTimeout(1000, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(1000, TimeUnit.SECONDS);
        return okHttpClient
    } catch (e: Exception) {
        return okHttpClient
    }
}
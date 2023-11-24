package com.fiveg.montenegreen.api

import com.fiveg.montenegreen.api.requests.LoginRequest
import com.fiveg.montenegreen.api.requests.RegisterRequest
import com.fiveg.montenegreen.api.responses.LoginResponse
import com.fiveg.montenegreen.api.responses.RegisterResponse
import com.fiveg.montenegreen.models.PopustModel
import com.fiveg.montenegreen.models.UserModel
import com.fiveg.montenegreen.models.ZadatakModel
import com.fiveg.montenegreen.util.GlobalData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

object Api {

    private val retrofit: Retrofit

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val headersInterceptor = Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("Accept", "application/json")
            chain.proceed(requestBuilder.build())
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(GlobalData.API_PREFIX)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: ApiInterface = retrofit.create()
}

interface ApiInterface {
    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body body: RegisterRequest): Call<RegisterResponse>

    @GET("auth/user")
    fun user(@Header("Authorization") token: String): Call<UserModel>

    @GET("auth/tasks")
    fun userTasks(@Header("Authorization") token: String): Call<ArrayList<ZadatakModel>>

    @GET("tasks")
    fun tasks(): Call<ArrayList<ZadatakModel>>

    @GET("discounts")
    fun discounts(): Call<ArrayList<PopustModel>>
}
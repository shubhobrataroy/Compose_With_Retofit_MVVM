package com.example.testcompose.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkRepository {
    private val retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
//                .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .baseUrl("https://dummy.restapiexample.com/")
            .build()
    }

    val employeeRestService by lazy { retrofit.create(EmployeeRestService::class.java) }
}
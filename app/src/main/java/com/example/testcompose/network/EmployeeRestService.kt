package com.example.testcompose.network

import retrofit2.Response
import retrofit2.http.GET

interface EmployeeRestService {

    @GET("/api/v1/employees")
    suspend fun getEmployeeList(): Response<EmployeeList>
}
package com.example.testcompose.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeList(
    @Json(name = "data")
    val employeeList: List<Employee>?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: String?
)
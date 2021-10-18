package com.example.testcompose.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    @Json(name = "employee_age")
    val employeeAge: Double?,
    @Json(name = "employee_name")
    val employeeName: String?,
    @Json(name = "employee_salary")
    val employeeSalary: Double?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "profile_image")
    val profileImage: String?
)
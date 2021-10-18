package com.example.testcompose

import androidx.lifecycle.*
import com.example.testcompose.network.Employee
import com.example.testcompose.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class EmployeeViewModel : ViewModel() {

    private val _employeeListState = MutableLiveData<CommonState<List<Employee>>>()
    val employeeListState: LiveData<CommonState<List<Employee>>> = _employeeListState

    fun getEmployeeData() {
        viewModelScope.launch(Dispatchers.IO) {
            _employeeListState.postValue(CommonState.Fetching)
            val employeeListResponse = NetworkRepository.employeeRestService.getEmployeeList()


            if (employeeListResponse.isSuccessful)
                _employeeListState.postValue(CommonState.Success(employeeListResponse.body().run {
                    if (this == null) emptyList()
                    else this.employeeList ?: emptyList()
                }))
            else
                _employeeListState.postValue(CommonState.Error(Exception("Api Error: ${employeeListResponse.code()}")))
        }
    }

}
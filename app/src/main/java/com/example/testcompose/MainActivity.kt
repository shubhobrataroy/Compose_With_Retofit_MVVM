package com.example.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.testcompose.network.Employee
import com.example.testcompose.ui.theme.TestComposeTheme
import androidx.compose.runtime.getValue

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getEmployeeData()
        setContent {
            TestComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    EmployeeDashboard(viewModel = viewModel)
                }
            }
        }
    }


}

@Composable
fun RetryView(
    viewModel: EmployeeViewModel,
    message: String = "Something went wrong. Please Try again",
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = message)
        Button(onClick = { viewModel.getEmployeeData() }) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun EmployeeDashboard(viewModel: EmployeeViewModel) {
    val employeeListFetchState by viewModel.employeeListState.observeAsState(initial = CommonState.Fetching)

    when (employeeListFetchState) {
        null -> RetryView(viewModel = viewModel)
        is CommonState.Error -> RetryView(
            message = (employeeListFetchState as CommonState.Error).exception.message
                ?: "Something went wrong. Please Try again", viewModel = viewModel
        )
        CommonState.Fetching -> Text(text = "Fetching . Please Wait")
        is CommonState.Success -> EmployeeListView((employeeListFetchState as CommonState.Success).data)
    }
}

@Composable
fun EmployeeView(employee: Employee) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
    ) {
        Column(
        ) {
            Text(text = "Name : " + employee.employeeName ?: "")
            Text(text = "Age: ${employee.employeeAge ?: 0}")
            Text(text = "Salary: ${employee.employeeSalary ?: 0}")
        }
    }

}


@Composable
fun EmployeeListView(employees: List<Employee>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentPadding = PaddingValues(top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        items(employees) { item: Employee ->
            EmployeeView(employee = item)
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val employee = Employee(
        30.0, "Test", 20000.00, 1, null
    )

    TestComposeTheme {
        EmployeeListView(employees = listOf(employee, employee))
    }
}
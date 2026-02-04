package ee.artjom.requesttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ee.artjom.requesttracker.ui.screens.AddScreen
import ee.artjom.requesttracker.ui.screens.ListScreen
import ee.artjom.requesttracker.ui.theme.RequestTrackerTheme
import ee.artjom.requesttracker.viewmodel.RequestsViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RequestTrackerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    val vm: RequestsViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "list"
                    ) {
                        composable("list") {
                            ListScreen(
                                vm = vm,
                                onAddClick = { navController.navigate("add") }
                            )
                        }
                        composable("add") {
                            AddScreen(
                                vm = vm,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
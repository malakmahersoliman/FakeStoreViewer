package com.example.fakestoreviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.fakestoreviewer.model.ResultState
import com.example.fakestoreviewer.ui.detail.DetailScreen
import com.example.fakestoreviewer.ui.home.HomeScreen
import com.example.fakestoreviewer.viewmodel.ProductViewModel
import com.example.fakestoreviewer.ui.theme.FakeStoreViewerTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Entry point of the Compose UI
        setContent {
            FakeStoreViewerTheme {
                // Launch main app content
                ProductApp()
            }
        }
    }
}

@Composable
fun ProductApp() {
    // Create a NavController for navigating between screens
    val navController = rememberNavController()
    // Get the shared ProductViewModel
    val viewModel: ProductViewModel = viewModel()

    // Set up navigation graph with NavHost
    NavHost(navController = navController, startDestination = "home") {
        // Home route — displays the product list
        composable("home") {
            HomeScreen(navController, viewModel)
        }

        composable(
            route = "detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("productId") ?: -1
            // Get product by ID from the ViewModel if in success state
            val product = (viewModel.productState as? ResultState.Success)?.data?.find { it.id == id }

            // Show product detail screen if product found, otherwise show fallback
            if (product != null) {
                DetailScreen(product, navController)
            } else {
                Text("Product not found.")
            }
        }

    }
}

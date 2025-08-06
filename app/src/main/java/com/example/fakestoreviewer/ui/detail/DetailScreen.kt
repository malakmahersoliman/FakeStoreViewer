package com.example.fakestoreviewer.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.fakestoreviewer.model.Product
import androidx.compose.material.icons.automirrored.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class) // Needed for TopAppBar in M3
@Composable
// This screen shows the full details of a selected product.
// It includes a top app bar with a back button and scrollable content.
fun DetailScreen(product: Product, navController: NavController) {
    // Scaffold provides a layout structure with top bar and body content.
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Product Details", style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                // Apply theme-based colors to the top bar.
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        // The main content column is scrollable in case the description is long.
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            //  Product image
            Image(
                painter = rememberAsyncImagePainter(product.images.firstOrNull()),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  Product title
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            //  Price in theme primary color
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  Product description
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

package com.example.fakestoreviewer.ui.home


import ads_mobile_sdk.h6
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.fakestoreviewer.viewmodel.ProductViewModel
import com.example.fakestoreviewer.model.Product
import androidx.navigation.NavController
import androidx.room.util.copy
import com.example.fakestoreviewer.model.ResultState




@OptIn(ExperimentalMaterial3Api::class)
// Observe the current state from ViewModel (loading, success, error)
@Composable
fun HomeScreen(
    // Observe the current state from ViewModel
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
) {
    val state = viewModel.productState

    Scaffold(
        topBar = {
            // TopAppBar with title
            TopAppBar(
                title = {
                    Text("FakeStore Products", style = MaterialTheme.typography.titleLarge)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        // Use a Box to center loading or error states
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            // Loading: show a spinner
            when (state) {
                is ResultState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                // Success: show list of product cards
                is ResultState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.data) { product ->
                            ProductCard(product) {
                                navController.navigate("detail/${product.id}")
                            }
                        }
                    }
                }
                // Error: show message and retry button
                is ResultState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Error: ${state.message}")
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { viewModel.fetchProducts() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

// Composable for displaying a single product card (image, title, price)
@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = 6.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.images.firstOrNull()),
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

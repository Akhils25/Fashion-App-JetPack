package com.oges.fashionapp.pages.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.oges.fashionapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { StylishHeader() },
        bottomBar = { StylishBottomNav() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFDFDFD))
        ) {
            // 1. Search Section
            item { SearchSection() }

            // 2. Featured Header with Sort/Filter
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("All Featured", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterButton(text = "Sort", icon = Icons.Default.Menu)
                        FilterButton(text = "Filter", icon = Icons.Default.Refresh)
                    }
                }
            }

            // 3. Horizontal Categories
            item {
                val categories = listOf("Beauty", "Fashion", "Kids", "Mens", "Womens")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(categories) { name ->
                        CategoryItem(name = name, image = R.drawable.ic_launcher_background)
                    }
                }
            }

            // 4. Stylish Banner
            item {
                StylishBanner(offer = "50-40% OFF")
            }

            // Add extra space at the bottom so content doesn't get cut off
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun StylishHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier.size(28.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Placeholder for Logo
            //Icon(Icons.Default.Adjust, contentDescription = null, tint = Color(0xFF4C86F9))
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Stylish",
                color = Color(0xFF4C86F9),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Surface(shape = CircleShape, modifier = Modifier.size(40.dp)) {
           // AsyncImage(model = "https://example.com/p.jpg", contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = { Text("Search any Product..", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        //trailingIcon = { Icon(Icons.Default.Mic, contentDescription = null) },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        // Adding a slight shadow effect via a Modifier shadow if needed
    )
}

@Composable
fun CategoryItem(name: String, image: Any) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = CircleShape,
            modifier = Modifier.size(60.dp),
            shadowElevation = 4.dp,
            color = Color.White
        ) {
            //AsyncImage(model = image, contentDescription = name, contentScale = ContentScale.Crop)
        }
        Text(text = name, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun StylishBanner(offer: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFD909E))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(offer, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Now in (product)\nAll colours", color = Color.White, fontSize = 14.sp)
                Spacer(Modifier.height(12.dp))
                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text("Shop Now →")
                }
            }
            // Add a placeholder for the girl image
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomEnd) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.3f),
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun FilterButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text, fontSize = 12.sp)
            Spacer(Modifier.width(4.dp))
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun StylishBottomNav() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) },
            label = { Text("Wishlist") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Cart") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Profile") },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}
package com.oges.fashionapp.pages.productDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavHostController) {
    var selectedSize by remember { mutableStateOf("7 UK") }
    val sizes = listOf("6 UK", "7 UK", "8 UK", "9 UK", "10 UK")

    Scaffold(
        bottomBar = { StickyBottomActions() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            HeaderActions(navController)

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)) {
                Image(
                    painter = rememberAsyncImagePainter("https://images.unsplash.com/photo-1542291026-7eec264c27ff"),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(5) { index ->
                        Box(
                            Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(if (index == 0) Color.Red else Color.LightGray)
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text("Size: $selectedSize", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(sizes) { size ->
                        SizeChip(size, isSelected = selectedSize == size) { selectedSize = size }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Nike Sneakers", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Vision Alta Men's Shoes Size (All Colours)",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    repeat(4) {
                        Icon(
                            Icons.Default.Star,
                            null,
                            tint = Color(0xFFFFB400),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Icon(
                        Icons.Default.Star,
                        null,
                        tint = Color(0xFFFFB400),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(" 56,890", fontSize = 12.sp, color = Color.LightGray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("₹1,500", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "₹2,999",
                        color = Color.Gray,
                        style = androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("50% Off", color = Color(0xFFFD6E87), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Product Details", fontWeight = FontWeight.Bold)
                Text(
                    "Perhaps the most iconic sneaker of all-time, this original 'Chicago' colorway is the cornerstone to any sneaker collection...",
                    fontSize = 13.sp, color = Color.DarkGray, lineHeight = 18.sp
                )
                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FeatureBadge("Nearest Store", Icons.Default.LocationOn)
                    FeatureBadge("VIP", Icons.Default.Star)
                    FeatureBadge("Return Policy", Icons.Default.Star)
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD9E1)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Delivery in 1 within Hour",
                        modifier = Modifier.padding(12.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeChip(size: String, isSelected: Boolean, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        colors = CardDefaults.outlinedCardColors(
            containerColor = if (isSelected) Color(0xFFFD6E87) else Color.White,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        border = BorderStroke(1.dp, if (isSelected) Color(0xFFFD6E87) else Color.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            size,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            fontSize = 12.sp
        )
    }
}

@Composable
fun StickyBottomActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E5BFF)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.Default.ShoppingCart, null)
            Spacer(Modifier.width(8.dp))
            Text("Go to cart")
        }
        Button(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56D18F)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.Default.Star, null)
            Spacer(Modifier.width(8.dp))
            Text("Buy Now")
        }
    }
}

@Composable
fun HeaderActions(navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.background(Color.White, CircleShape)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
        IconButton(onClick = { }, modifier = Modifier.background(Color.White, CircleShape)) {
            Icon(Icons.Default.ShoppingCart, contentDescription = null)
        }
    }
}

@Composable
fun FeatureBadge(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
        Text(label, fontSize = 10.sp, color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
    }
}
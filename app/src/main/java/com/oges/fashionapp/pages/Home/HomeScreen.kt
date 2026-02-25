package com.oges.fashionapp.pages.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.oges.fashionapp.R
import com.oges.fashionapp.model.CategoryListingModel
import com.oges.fashionapp.model.ProductListingModel
import com.oges.fashionapp.pages.StylishHeader
import com.oges.fashionapp.ui.theme.Background
import com.oges.fashionapp.ui.theme.ReddishPink
import com.oges.fashionapp.ui.theme.SkyBlue
import com.oges.fashionapp.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val products by viewModel.products.observeAsState(initial = emptyList())
    val category by viewModel.categories.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    Scaffold(
        topBar = { StylishHeader() },
        bottomBar = { StylishBottomNav() }
    ) { paddingValues ->
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = ReddishPink)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = Background)
            ) {
                item { SearchSection() }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(R.string.all_featured),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilterButton(text = "Sort", icon = Icons.Default.Menu)
                            FilterButton(text = "Filter", icon = Icons.Default.Refresh)
                        }
                    }
                }

                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        items(category) { item ->
                            CategoryItem(navController, item)
                        }
                    }
                }

                item {
                    StylishBanner(offer = "50-40% OFF")
                }

                item { Spacer(modifier = Modifier.height(20.dp)) }

                item {
                    DealOfTheDaySection(navController, products)
                }
                item { SpecialOfferSection() }

                item {
                    SummerSaleBanner()
                }
                item {
                    ScrollableProductRow(navController, products.reversed())
                }
                item {
                    TrendingBanner()
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollableProductRow(
    navController: NavHostController,
    product: List<ProductListingModel.Product>
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(product) { ProductCard(navController, it) }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(36.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 4.dp,
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun TrendingBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = ReddishPink),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.trending_products),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Last Date 29/02/22",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            OutlinedButton(
                onClick = { },
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Text(stringResource(R.string.view_all), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SummerSaleBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Yellow)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = R.drawable.spalsh_bg_img,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.7f))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.new_arrivals),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = stringResource(R.string.summer_25_collections),
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = ReddishPink),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(stringResource(R.string.view_all), color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun DealOfTheDaySection(
    navController: NavHostController,
    product: List<ProductListingModel.Product>
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SkyBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        stringResource(R.string.deal_of_the_day),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.AccountBox,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("22h 55m 20s remaining", color = Color.White, fontSize = 12.sp)
                    }
                }
                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    Text(stringResource(R.string.view_all), color = Color.White, fontSize = 12.sp)
                }
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(product) {
                ProductCard(navController, it)
            }
        }
    }
}

@Composable
fun ProductCard(navController: NavHostController, product: ProductListingModel.Product) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = androidx.compose.material.ripple.rememberRipple(),
                onClick = { navController.navigate("product_details/${product.productId}") }
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            AsyncImage(
                model = product.productImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(product.productName, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(
                    product.productRemarks,
                    fontSize = 10.sp,
                    maxLines = 2,
                    color = Color.Gray
                )
                Text(
                    "${product.currency}${product.sellingPrice}",
                    fontWeight = FontWeight.ExtraBold
                )
                Row {
                    Text(
                        "${product.currency}${product.productMRP}",
                        fontSize = 10.sp,
                        style = androidx.compose.ui.text.TextStyle(textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "${product.offerValues}${product.offerType}",
                        color = ReddishPink,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SpecialOfferSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text("Special Offers 🤩", fontWeight = FontWeight.Bold)
                Text(
                    "We make sure you get the offer you need at best prices",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
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
        placeholder = {
            Text(
                stringResource(R.string.search_any_product),
                color = Color.LightGray
            )
        },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(navController: NavHostController, item: CategoryListingModel.Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = CircleShape,
            shadowElevation = 4.dp,
            color = Color.White,
            onClick = { navController.navigate("product_listing/${item.catId}") }
        ) {
            AsyncImage(
                model = item.catImage,
                contentDescription = "category Image",
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = item.catName, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
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
        colors = CardDefaults.cardColors(containerColor = ReddishPink)
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
                    Text(stringResource(R.string.shop_now))
                }
            }
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
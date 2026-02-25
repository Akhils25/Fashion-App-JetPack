package com.oges.fashionapp.pages.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.oges.fashionapp.R
import com.oges.fashionapp.pages.BottomNavScreen
import com.oges.fashionapp.ui.theme.Red
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(BottomNavScreen.Home.route) {
            popUpTo("SplashPage") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.spalsh_bg_img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "You want Authentic, here you go!",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 55.sp,
                    lineHeight = 60.sp,
                    color = Color.White
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 50.dp, vertical = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Divider(modifier = Modifier.fillMaxWidth(), 5.dp, Color.Transparent)
            Text(
                text = "Find it here, buy it now!",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                ),
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Divider(modifier = Modifier.fillMaxWidth(), 40.dp, Color.Transparent)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 32.dp)
                    .background(
                        color = Red,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 13.dp)
                )
            }
            Divider(modifier = Modifier.fillMaxWidth(), 20.dp, Color.Transparent)
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ShowScreen() {
    val dummyNavController = rememberNavController()
    SplashPage(navController = dummyNavController)
}
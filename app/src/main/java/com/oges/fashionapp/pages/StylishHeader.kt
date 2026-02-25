package com.oges.fashionapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.oges.fashionapp.R

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
        Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier.size(28.dp), tint = Color.Black)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_logo_fashion),
                contentDescription = "Local Image",
                modifier = Modifier
                    .width(110.dp)
                    .height(35.dp)
            )
        }

        Surface(shape = CircleShape, modifier = Modifier.size(40.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_img),
                contentDescription = "Local Image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
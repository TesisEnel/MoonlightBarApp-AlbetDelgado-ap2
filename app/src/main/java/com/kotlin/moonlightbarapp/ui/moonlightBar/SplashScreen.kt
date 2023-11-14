package com.kotlin.moonlightbarapp.ui.moonlightBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.theme.MoonlightBarAppTheme
import com.kotlin.moonlightbarapp.ui.theme.Morado40


@Preview
@Composable
fun SplashScreen() {
    MoonlightBarAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Morado40),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logoblanco),
                contentDescription = null,
                alignment = Alignment.Center
            )
            Text(text = "Moonlight Bar App",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Cursive,
               fontSize = 30.sp
            )
        }
    }
}
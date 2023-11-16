package com.kotlin.moonlightbarapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.theme.Morado90

@Preview
@Composable
fun LoginScreen(){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
            .background(color = Morado40)
    ){
        Image(
            painter = painterResource(id = R.drawable.logoblanco),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside
        )

        Box(
            modifier = Modifier.fillMaxSize()
                .padding(3.dp),
            contentAlignment = Alignment.BottomCenter,
        ){
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                color = Color.White,
                shape = RoundedCornerShape(
                    topStartPercent = 8,
                    topEndPercent = 8
                )
            ) {

            }

            FloatingActionButton(
                modifier = Modifier.size(72.dp),
                containerColor = Morado90,
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(42.dp),
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Forward Icon",
                    tint = Color.White
                )

            }
        }
    }
}

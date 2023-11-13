import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.ui.moonlightBar.CotelTopBar

@Composable
fun CopaLiquido() {
    var progress by remember { mutableStateOf(0f) }

    // Configuración de la animación
    val transition = updateTransition(targetState = progress, label = "progress")

    // Escala para simular el llenado de líquido
    val scale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000, easing = FastOutSlowInEasing) },
        label = "scale"
    ) {
        if (it == 1f) 1.01f else it
    }

    // Configuración de la animación de color
    val color by transition.animateColor(
        transitionSpec = { tween(durationMillis = 1000, easing = FastOutSlowInEasing) },
        label = "color"
    ) {
        if (it == 1f) Color.Green else Color.Blue
    }

    // Diseño de la copa
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        // Copa
        Box(
            modifier = Modifier
                .size(100.dp, 200.dp)
                .background(color = Color.Gray)
                .clip(CircleShape)
        ) {
            // Líquido
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(scale)
                    .background(color = color)
                    .clip(CircleShape)
            )
        }

        // Botón para llenar la copa
        Button(onClick = {
            progress = 1f
        }) {
            Text("Llenar Copa")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // CustomArc()
        CopaLiquido()


    }
}


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.theme.Morado40

@Composable
fun IngredientsList(ingredients: List<Pair<String, Int>>) {
    var progress by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Scroll horizontal de ingredientes
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Morado40)
                .padding(8.dp)
        ) {
            items(ingredients) { (name, imageRes) ->
                IngredientCard(name = name, imageRes = imageRes)
                progress += 1.0f / ingredients.size
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de progreso
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun IngredientCard(name: String, imageRes: Int) {
    Column(
        modifier = Modifier
            .width(80.dp)  // Ajusta este valor
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)  // Ajusta este valor
                .height(80.dp)  // Ajusta este valor
                .clip(CircleShape)
                .background(Color.White)

        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(60.dp)  // Ajusta este valor
                    .width(60.dp)   // Ajusta este valor
            )
        }
    }
    Spacer(modifier = Modifier.height(50.dp))

    // Columna para el nombre del ingrediente
    Box(
        contentAlignment = Alignment.CenterEnd
        ,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}







@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IngredientsList(
        ingredients = listOf(
            Pair("Ingrediente 1", R.drawable.licor),
            Pair("Ingrediente 2", R.drawable.licor__1_),
            Pair("Ingrediente 3", R.drawable.licor__2_),
            Pair("Ingrediente 4", R.drawable.licor__3_),
            Pair("Ingrediente 5", R.drawable.licor)
        )
    )
}


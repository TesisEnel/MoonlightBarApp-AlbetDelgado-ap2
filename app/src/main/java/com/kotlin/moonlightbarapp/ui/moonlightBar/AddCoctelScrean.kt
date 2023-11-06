package com.kotlin.moonlightbarapp.ui.moonlightBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCoctelScrean() {

    val sizeImagen = 70.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        )
    {
        var text by rememberSaveable { mutableStateOf("Mi trago") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .weight(1f)
                .size(sizeImagen)
        )
        Image(
            painter = painterResource(id = R.drawable.coctel1),
            contentDescription = "probando",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(sizeImagen)
        )
    }
}
//        Text(
////            text="Mi Margarita", style = MaterialTheme.typography.headlineMedium)
//        Text(
//            text="Las Margarita es un clásico. Es el cóctel a base de tequila más popular de Estados Unidos.",
//            style = MaterialTheme.typography.bodyMedium)
//        Text(
//            "Ingredientes:", style = MaterialTheme.typography.headlineMedium)
//
//        val ingredients = listOf(
//            "Tequila - 50 ml - Opcional",
//            "Guarnición - 10 ml - Opcional",
//            "Azúcar - 2 grs - Opcional",
//            "Garnish - Opcional"
//        )
//
//        ingredients.forEach { ingredient ->
//            Text(ingredient, style = MaterialTheme.typography.bodyMedium)
//        }
//
//        Button(onClick = { /* TODO: Handle click */ }) {
//            Text("Agregar más ingredientes")
//        }
//    }

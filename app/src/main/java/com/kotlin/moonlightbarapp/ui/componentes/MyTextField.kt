package com.kotlin.moonlightbarapp.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.ui.theme.Morado30
import com.kotlin.moonlightbarapp.ui.theme.Morado40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    valor: String,
    alCambiarValor: (String) -> Unit,
    textoQueDesaparece: String? = null,
    textoQueSube: String? = null,
    iconoIzquierdo:( @Composable () -> Unit)?=null,
    iconoDerecho:( @Composable () -> Unit)?=null,
    modificador: Modifier = Modifier,


    ) {

    TextField(
        value = valor,
        onValueChange = alCambiarValor,

        placeholder = if (textoQueDesaparece != null) {
            {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = textoQueDesaparece
                )
            }
        } else {
            {}
        },
        label = if (textoQueSube != null) {
            {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = textoQueSube
                )
            }

        } else {
            {}
        },
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Morado40,
            unfocusedSupportingTextColor = Morado40,
            focusedSupportingTextColor = Morado30,
            textColor = Morado40,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = Morado30,
            focusedLabelColor = Morado30,
            focusedTrailingIconColor = Morado30,
            unfocusedLabelColor = Morado40,
            unfocusedLeadingIconColor = Morado40,
            unfocusedTrailingIconColor = Morado40,
            placeholderColor = Morado30,


            ),

        modifier = modificador
            .padding(4.dp)
            .wrapContentHeight(
                Alignment.CenterVertically
            )
            .fillMaxWidth(),

        leadingIcon = iconoIzquierdo,
        trailingIcon = iconoDerecho


    )


}

@Preview(showSystemUi = true)
@Composable
fun ver() {
    Column {
        MyTextField(iconoIzquierdo =
        { Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )},
            valor =  "",
            textoQueDesaparece = "Buscar coctel",
            alCambiarValor ={},
            iconoDerecho ={
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
            )
    }

}
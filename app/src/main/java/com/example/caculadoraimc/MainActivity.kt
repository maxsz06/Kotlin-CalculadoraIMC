package com.example.caculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caculadoraimc.ui.theme.CaculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {

        var textAlturaValue by remember { mutableStateOf("") }
        var textPesoValue by remember { mutableStateOf("") }
        var imcCalculo by remember { mutableStateOf("") }
        var imcResultado by remember { mutableStateOf("") }
        var corResultado by remember { mutableStateOf(Color(0xFF4FA475)) }


        //--------- HEADER -----------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(id = R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "bmi",
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        //-------- FORMULÁRIO ---------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    text = "Seus dados",
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.cor_app),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                        .align (Alignment.CenterHorizontally)
                )

                OutlinedTextField(
                    value = textAlturaValue,
                    onValueChange = { novoValor -> textAlturaValue = novoValor },
                    label = { Text("Altura") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(id = R.color.cor_app),
                        unfocusedBorderColor = colorResource(id = R.color.cor_app)
                    )
                )

                OutlinedTextField(
                    value = textPesoValue,
                    onValueChange = { novoValor -> textPesoValue = novoValor },
                    label = { Text("Peso") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(id = R.color.cor_app),
                        unfocusedBorderColor = colorResource(id = R.color.cor_app)
                    )
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp)
                        .offset(y = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.cor_app)
                    ),
                    onClick = {
                        val resultado = calcularImc(textAlturaValue, textPesoValue)

                        imcCalculo = resultado.calculo
                        imcResultado = resultado.classificacao
                        corResultado = resultado.cor
                    }
                ) {
                    Text("Calcular")
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(250.dp)
                    .height(75.dp),
                    colors = CardDefaults.cardColors(
                    containerColor = corResultado
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = imcCalculo,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = imcResultado,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun IMCScreenPreview() {
    CaculadoraIMCTheme {
        IMCScreen()
    }
}
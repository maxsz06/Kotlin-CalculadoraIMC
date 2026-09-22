package com.example.caculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(modifier = Modifier.fillMaxWidth()) {
                //--------- HEADER
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .background(color = colorResource(id = R.color.cor_app))
                        .height(160.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.bmi),
                        contentDescription ="bmi",
                        modifier = Modifier.size(80.dp)
                                           .padding(16.dp)
                    )
                }
                //-------- Formulario
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {

                }
            }
        }
        //card resultado
    }
}

@Preview(showBackground = true)
@Composable
fun IMCScreenPreview() {
    CaculadoraIMCTheme {
        IMCScreen()
    }
}
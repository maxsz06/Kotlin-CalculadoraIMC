package com.example.caculadoraimc

import androidx.compose.ui.graphics.Color

data class ImcResultado(
    val calculo: String,
    val classificacao: String,
    val cor: Color
)

private val VerdeNormal = Color(0xFF4FA475)

fun calcularImc(alturaTexto: String, pesoTexto: String): ImcResultado {
    val altura = alturaTexto.replace(",", ".").toDoubleOrNull()
    val peso = pesoTexto.replace(",", ".").toDoubleOrNull()

    if (altura == null || peso == null || altura <= 0.0 || peso <= 0.0) {
        return ImcResultado(
            calculo = "Digite valores válidos",
            classificacao = "",
            cor = VerdeNormal
        )
    }

    val imc = peso / (altura * altura)
    val calculo = "IMC: " + String.format("%.1f", imc)

    val (classificacao, cor) = when {
        imc < 18.5 -> "Abaixo do peso" to Color(0xFF42A5F5)
        imc < 25.0 -> "Peso normal" to VerdeNormal
        imc < 30.0 -> "Sobrepeso" to Color(0xFFF9A825)
        imc < 35.0 -> "Obesidade grau I" to Color(0xFFFB8C00)
        imc < 40.0 -> "Obesidade grau II" to Color(0xFFE53935)
        else -> "Obesidade grau III" to Color(0xFFB71C1C)
    }

    return ImcResultado(calculo, classificacao, cor)
}
package com.guilherme.impressao_etiqueta.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guilherme.impressao_etiqueta.viewmodel.EtiquetaViewModel

@Composable
fun MaterialScreen(navController: NavHostController,    viewModel: EtiquetaViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                viewModel.material.value = "PE"
                navController.navigate("tipo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("PE")
        }

        Button(
            onClick = {
                viewModel.material.value = "PP"
                navController.navigate("tipo") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("PP")
        }
    }
}
package com.guilherme.impressao_etiqueta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guilherme.impressao_etiqueta.ui.theme.ImpressaoetiquetaTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.guilherme.impressao_etiqueta.ui.screens.HomeScreen
import com.guilherme.impressao_etiqueta.ui.screens.ImprimirScreen
import com.guilherme.impressao_etiqueta.ui.screens.MaterialScreen
import com.guilherme.impressao_etiqueta.ui.screens.NovaEtiquetaScreen
import com.guilherme.impressao_etiqueta.ui.screens.ProdutoScreen
import com.guilherme.impressao_etiqueta.ui.screens.TamanhoScreen
import com.guilherme.impressao_etiqueta.ui.screens.QuantidadeScreen
import com.guilherme.impressao_etiqueta.ui.screens.RepetirEtiquetaScreen
import com.guilherme.impressao_etiqueta.ui.screens.TipoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("produto") { ProdutoScreen(navController) }
        composable("material") { MaterialScreen(navController) }
        composable("tipo") { TipoScreen(navController) }
        composable("quantidade") { QuantidadeScreen(navController) }
        composable("tamanho") { TamanhoScreen(navController) }
        composable("imprimir") { ImprimirScreen(navController) }
        composable("repetir_etiqueta") { RepetirEtiquetaScreen(navController) }
    }
}
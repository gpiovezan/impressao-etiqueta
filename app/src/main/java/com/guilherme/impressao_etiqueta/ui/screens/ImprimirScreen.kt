package com.guilherme.impressao_etiqueta.ui.screens

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.drawToBitmap
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.guilherme.impressao_etiqueta.viewmodel.EtiquetaViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun ImprimirScreen(
    navController: NavHostController,
    viewModel: EtiquetaViewModel
) {
    val context = LocalContext.current

    // Armazena uma referência para o ComposeView
    var composeView: ComposeView? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibe a etiqueta visualmente
        AndroidView(
            factory = { ctx ->
                ComposeView(ctx).apply {
                    setContent {
                        EtiquetaView(viewModel)
                    }
                    composeView = this
                }
            },
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                composeView?.post {
                    try {
                        val bitmap = composeView?.drawToBitmap()
                        if (bitmap != null) {
                            val file = File(
                                context.getExternalFilesDir(null),
                                "etiqueta_${System.currentTimeMillis()}.png"
                            )
                            FileOutputStream(file).use {
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                            }
                            Toast.makeText(
                                context,
                                "Etiqueta salva em ${file.absolutePath}",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Erro ao capturar imagem",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navController.navigate("home")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(context, "Erro ao salvar etiqueta", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Imprimir")
        }
    }
}



// Layout da etiqueta renderizada
@Composable
fun EtiquetaView(viewModel: EtiquetaViewModel) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(viewModel.produto.value, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "${viewModel.tamanho.value} cm ${viewModel.tipo.value}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Contém: ")
        Text(
            "${viewModel.quantidade.value}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text("± ${viewModel.unidades.value} unidades")
        Spacer(modifier = Modifier.height(8.dp))
        Text("www.suaempresa.com.br", fontSize = 12.sp)
    }
}

// Função para salvar a imagem no armazenamento
fun saveBitmapToStorage(context: Context, bitmap: Bitmap): android.net.Uri? {
    val filename = "etiqueta_${System.currentTimeMillis()}.png"
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Etiquetas")
    }

    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    uri?.let {
        resolver.openOutputStream(it)?.use { stream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        }
    }
    return uri
}

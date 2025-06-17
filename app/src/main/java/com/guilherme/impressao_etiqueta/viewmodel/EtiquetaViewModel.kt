package com.guilherme.impressao_etiqueta.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EtiquetaViewModel : ViewModel() {
    var produto = mutableStateOf("")
    var material = mutableStateOf("")
    var tipo = mutableStateOf("")
    var quantidade = mutableStateOf("")
    var tamanho = mutableStateOf("")
    var unidades = mutableStateOf("")


    fun resetar() {
        produto.value = ""
        material.value = ""
        tipo.value = ""
        quantidade.value = ""
        tamanho.value = ""
        unidades.value = ""
    }
}

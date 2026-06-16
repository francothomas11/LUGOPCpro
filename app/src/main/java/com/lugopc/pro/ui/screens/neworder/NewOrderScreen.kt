package com.lugopc.pro.ui.screens.neworder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lugopc.pro.ui.viewmodels.ServiceOrderViewModel
import com.lugopc.pro.domain.models.ServiceOrder
import java.time.LocalDateTime

@Composable
fun NewOrderScreen(
    navController: NavHostController,
    viewModel: ServiceOrderViewModel = hiltViewModel()
) {
    var clientName by remember { mutableStateOf("") }
    var clientPhone by remember { mutableStateOf("") }
    var equipmentType by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val nextFolio by viewModel.nextFolio.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Orden de Servicio") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Folio Display
                Text(
                    text = "Folio: $nextFolio",
                    style = MaterialTheme.typography.titleMedium
                )
                Divider()
            }
            
            item {
                // Client Name
                OutlinedTextField(
                    value = clientName,
                    onValueChange = { clientName = it },
                    label = { Text("Nombre del Cliente") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            item {
                // Client Phone
                OutlinedTextField(
                    value = clientPhone,
                    onValueChange = { clientPhone = it },
                    label = { Text("Teléfono del Cliente") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            item {
                // Equipment Type
                OutlinedTextField(
                    value = equipmentType,
                    onValueChange = { equipmentType = it },
                    label = { Text("Tipo de Equipo") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            item {
                // Description
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción del Servicio") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 4
                )
            }
            
            item {
                // Create Button
                Button(
                    onClick = {
                        if (clientName.isNotEmpty() && clientPhone.isNotEmpty() && equipmentType.isNotEmpty()) {
                            isLoading = true
                            val newOrder = ServiceOrder(
                                folio = nextFolio,
                                clientName = clientName,
                                clientPhone = clientPhone,
                                equipmentType = equipmentType,
                                serviceDescription = description,
                                createdAt = LocalDateTime.now()
                            )
                            viewModel.createOrder(newOrder)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = !isLoading
                ) {
                    Text("Crear Orden")
                }
            }
        }
    }
}

package com.lugopc.pro.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lugopc.pro.ui.navigation.NavRoutes
import com.lugopc.pro.ui.viewmodels.ServiceOrderViewModel
import com.lugopc.pro.domain.models.ServiceStatus

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ServiceOrderViewModel = hiltViewModel()
) {
    val orders by viewModel.orders.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var selectedStatus by remember { mutableStateOf(ServiceStatus.RECEIVED) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LUGOPC Pro - Ordenes de Servicio") },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavRoutes.NewOrder.route) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Orden")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Status Filter
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(ServiceStatus.values()) { status ->
                    FilterChip(
                        selected = selectedStatus == status,
                        onClick = {
                            selectedStatus = status
                            viewModel.loadOrdersByStatus(status)
                        },
                        label = { Text(status.name) }
                    )
                }
            }
            
            // Orders List
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(orders) { order ->
                        ServiceOrderCard(order = order)
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceOrderCard(order: com.lugopc.pro.domain.models.ServiceOrder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Folio: ${order.folio}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Cliente: ${order.clientName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Teléfono: ${order.clientPhone}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Equipo: ${order.equipmentType}",
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Estado: ${order.status.name}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Costo: \$${order.estimatedCost}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

package com.lugopc.pro.ui.screens.inventory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lugopc.pro.ui.viewmodels.InventoryViewModel
import com.lugopc.pro.domain.models.InventoryItem

@Composable
fun InventoryScreen(
    navController: NavHostController,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    var showAddItemDialog by remember { mutableStateOf(false) }
    val items by viewModel.items.collectAsState()
    val lowStockItems by viewModel.lowStockItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var filterLowStock by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventario") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddItemDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Artículo")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Filter Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = filterLowStock,
                    onClick = { filterLowStock = !filterLowStock },
                    label = { Text("Stock Bajo") },
                    leadingIcon = if (filterLowStock) {
                        { Icon(Icons.Default.Warning, contentDescription = "Warning") }
                    } else null
                )
            }
            
            // Items List
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                val displayItems = if (filterLowStock) lowStockItems else items
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(displayItems) { item ->
                        InventoryItemCard(item = item)
                    }
                }
            }
        }
    }
    
    if (showAddItemDialog) {
        AddInventoryItemDialog(
            onDismiss = { showAddItemDialog = false },
            onAddItem = { name, category, quantity, minQuantity, unitCost ->
                val newItem = InventoryItem(
                    name = name,
                    category = category,
                    quantity = quantity,
                    minimumQuantity = minQuantity,
                    unitCost = unitCost
                )
                viewModel.addItem(newItem)
                showAddItemDialog = false
            }
        )
    }
}

@Composable
fun InventoryItemCard(item: InventoryItem) {
    val isLowStock = item.quantity <= item.minimumQuantity
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = if (isLowStock) Color(0xFFFFEBEE) else MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Categoría: ${item.category}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                if (isLowStock) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Stock bajo",
                        tint = Color.Red
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Stock: ${item.quantity}/${item.minimumQuantity}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Costo: \$${item.unitCost}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun AddInventoryItemDialog(
    onDismiss: () -> Unit,
    onAddItem: (String, String, Int, Int, Double) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var minQuantity by remember { mutableStateOf("") }
    var unitCost by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Artículo") },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = { Text("Categoría") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    OutlinedTextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        label = { Text("Cantidad") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    OutlinedTextField(
                        value = minQuantity,
                        onValueChange = { minQuantity = it },
                        label = { Text("Cantidad Mínima") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    OutlinedTextField(
                        value = unitCost,
                        onValueChange = { unitCost = it },
                        label = { Text("Costo Unitario") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotEmpty() && category.isNotEmpty()) {
                        onAddItem(
                            name,
                            category,
                            quantity.toIntOrNull() ?: 0,
                            minQuantity.toIntOrNull() ?: 0,
                            unitCost.toDoubleOrNull() ?: 0.0
                        )
                    }
                }
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

package com.lugopc.pro.ui.screens.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lugopc.pro.ui.theme.LugopcBlue
import com.lugopc.pro.ui.theme.LugopcGreen
import com.lugopc.pro.ui.theme.LugopcOrange

@Composable
fun ReportsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reportes") },
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
                // Summary Cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatCard(
                        title = "Total Servicios",
                        value = "156",
                        color = LugopcBlue,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Ingresos",
                        value = "\$45,230",
                        color = LugopcGreen,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Promedio Rating",
                        value = "4.8★",
                        color = LugopcOrange,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            item {
                Divider()
            }
            
            item {
                // Services by Status
                Text(
                    text = "Servicios por Estado",
                    style = MaterialTheme.typography.titleMedium
                )
                ServiceStatusChart()
            }
            
            item {
                Divider()
            }
            
            item {
                // Top Technicians
                Text(
                    text = "Técnicos Destacados",
                    style = MaterialTheme.typography.titleMedium
                )
                TechnicianRankingItem(rank = 1, name = "Juan López", services = 52, rating = 4.9f)
                TechnicianRankingItem(rank = 2, name = "Carlos Martínez", services = 48, rating = 4.7f)
                TechnicianRankingItem(rank = 3, name = "Miguel Ríos", services = 45, rating = 4.6f)
            }
            
            item {
                Divider()
            }
            
            item {
                // Export Options
                Text(
                    text = "Exportar Reportes",
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { /* Export to PDF */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("PDF")
                    }
                    Button(
                        onClick = { /* Export to Excel */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Excel")
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = color
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = androidx.compose.ui.graphics.Color.White
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = androidx.compose.ui.graphics.Color.White
            )
        }
    }
}

@Composable
fun ServiceStatusChart() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChartBar(label = "Recibidas", value = 45, maxValue = 160, color = LugopcBlue)
        ChartBar(label = "En Proceso", value = 78, maxValue = 160, color = LugopcOrange)
        ChartBar(label = "Completadas", value = 28, maxValue = 160, color = LugopcGreen)
        ChartBar(label = "Canceladas", value = 9, maxValue = 160, color = androidx.compose.ui.graphics.Color.Red)
    }
}

@Composable
fun ChartBar(
    label: String,
    value: Int,
    maxValue: Int,
    color: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.width(80.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(24.dp)
                .fillMaxWidth(fraction = value.toFloat() / maxValue)
                .also { it.then(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                    )
                }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = value.toFloat() / maxValue),
                color = color
            ) {}
        }
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.width(40.dp)
        )
    }
}

@Composable
fun TechnicianRankingItem(
    rank: Int,
    name: String,
    services: Int,
    rating: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#$rank",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.width(40.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$services servicios completados",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = "★ $rating",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

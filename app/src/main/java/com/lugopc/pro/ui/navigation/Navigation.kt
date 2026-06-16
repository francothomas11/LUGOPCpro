package com.lugopc.pro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lugopc.pro.ui.screens.login.LoginScreen
import com.lugopc.pro.ui.screens.home.HomeScreen
import com.lugopc.pro.ui.screens.neworder.NewOrderScreen
import com.lugopc.pro.ui.screens.payment.PaymentManagementScreen
import com.lugopc.pro.ui.screens.inventory.InventoryScreen
import com.lugopc.pro.ui.screens.reports.ReportsScreen

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Home : NavRoutes("home")
    object NewOrder : NavRoutes("neworder")
    object ServiceTracking : NavRoutes("servicetracking")
    object PaymentManagement : NavRoutes("paymentmanagement")
    object Inventory : NavRoutes("inventory")
    object Reports : NavRoutes("reports")
    object TechnicianPanel : NavRoutes("technicianpanel")
}

@Composable
fun LugopcNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Login.route
    ) {
        composable(NavRoutes.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.NewOrder.route) {
            NewOrderScreen(navController = navController)
        }
        composable(NavRoutes.PaymentManagement.route) {
            PaymentManagementScreen(navController = navController)
        }
        composable(NavRoutes.Inventory.route) {
            InventoryScreen(navController = navController)
        }
        composable(NavRoutes.Reports.route) {
            ReportsScreen(navController = navController)
        }
    }
}

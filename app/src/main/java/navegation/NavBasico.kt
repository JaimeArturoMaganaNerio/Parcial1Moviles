package navegation

import Routes.AppRoutes
import androidx.compose.runtime.*
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import models.Producto
import screen.MenuScreen
import screen.OrderScreen

@Composable
fun BasicNavigation() {

    val order = remember { mutableStateMapOf<Producto, Int>() }
    

    val backStack = rememberNavBackStack(AppRoutes.MenuScreen)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<AppRoutes.MenuScreen> {
                MenuScreen(
                    order = order,
                    onAddProduct = { producto ->
                        val actual = order[producto] ?: 0
                        order[producto] = actual + 1
                    },
                    onNavigateToOrder = { 
                        backStack.add(AppRoutes.OrderScreen) 
                    }
                )
            }
            

            entry<AppRoutes.OrderScreen> {
                OrderScreen(
                    order = order,
                    onRemoveProduct = { producto ->
                        val actual = order[producto] ?: 0
                        if (actual > 1) {
                            order[producto] = actual - 1
                        } else {
                            order.remove(producto)
                        }
                    },
                    onClearOrder = { 
                        order.clear() 
                    },
                    navigateUp = { 
                        backStack.removeLastOrNull() 
                    }
                )
            }
        }
    )
}

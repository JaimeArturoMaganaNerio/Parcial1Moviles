package screen

import models.Producto
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    order: Map<Producto, Int>,
    onRemoveProduct: (Producto) -> Unit,
    onClearOrder: () -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val total = order.entries.sumOf { it.key.precio * it.value }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Orden") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) { Icon(Icons.Default.ArrowBack, null) }
                }
            )
        },
        bottomBar = {
            if (order.isNotEmpty()) {
                BottomAppBar {
                    Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.SpaceBetween) {
                        Text("Total: $$total")
                        Button(onClick = {
                            onClearOrder()
                            Toast.makeText(context, "Orden confirmada", Toast.LENGTH_SHORT).show()
                            navigateUp()
                        }) { Text("Confirmar") }
                    }
                }
            }
        }
    ) { padding ->
        if (order.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Orden vacía") }
        } else {
            LazyColumn(contentPadding = padding) {
                items(order.entries.toList()) { (producto, quantity) ->
                    Card(Modifier.fillMaxWidth().padding(8.dp)) {
                        Row(Modifier.padding(16.dp), Arrangement.SpaceBetween) {
                            Column {
                                Text(producto.nombre)
                                Text("Cant: $quantity | Subtotal: $${String.format("%.2f", producto.precio * quantity)}")
                            }
                            IconButton(onClick = { onRemoveProduct(producto) }) {
                                Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}


package screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dummy.menu
import models.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    order: Map<Producto, Int>,
    onAddProduct: (Producto) -> Unit,
    onNavigateToOrder: () -> Unit
) {

    val totalItems = order.values.sum()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú de Pupusas") },
                actions = {
                    IconButton(onClick = onNavigateToOrder) {
                        BadgedBox(
                            badge = {
                                if (totalItems > 0) {
                                    Badge { Text("$totalItems") }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Ver Orden"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(menu) { producto ->
                val cantidad = order[producto] ?: 0
                ProductoItem(
                    producto = producto,
                    cantidad = cantidad,
                    onAgregar = { onAddProduct(producto) }
                )
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, cantidad: Int, onAgregar: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { onAgregar() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${producto.precio}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (cantidad > 0) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "x$cantidad",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewMenuConDatos() {
    val ordenDePrueba = mapOf(menu[0] to 1, menu[5] to 1)

    MenuScreen(
        order = ordenDePrueba,
        onAddProduct = {},
        onNavigateToOrder = {}
    )
}
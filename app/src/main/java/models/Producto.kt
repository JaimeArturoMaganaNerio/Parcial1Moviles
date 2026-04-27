package models


enum class TipoProducto {
    PUPUSA,
    BEBIDA
}

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String,
    val tipo: TipoProducto
)


package Routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes: NavKey {
    @Serializable
    data object MenuScreen: AppRoutes
    @Serializable
    data object OrderScreen: AppRoutes
}
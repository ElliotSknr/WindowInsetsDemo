import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import windowinsetsdemo.composeapp.generated.resources.Res
import windowinsetsdemo.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    val useNestedNavigators = true
    if (useNestedNavigators) {
        Navigator(MainScreen, key = "MainNavigator") { navigator ->
            CurrentScreen()
        }
    } else {
        TabNavigator(HomeTab, key = "TabNavigator") { tabNavigator ->
            CurrentTab()
        }
    }
}

object MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(HomeTab, key = "TabNavigator") { tabNavigator ->
            CurrentTab()
        }
    }
}

object HomeTab : Tab {
    @Composable
    override fun Content() {
        Scaffold {
            Box(Modifier.padding(it).consumeWindowInsets(it).fillMaxSize().background(Color.Red))
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    override val options: TabOptions
        @Composable
        get() {
            val icon = painterResource(Res.drawable.compose_multiplatform)
            return TabOptions(0u, title = "Home", icon = icon)
        }
}

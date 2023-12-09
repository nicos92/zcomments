import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import composables.columnMain

@Composable
@Preview
fun App() {

    MaterialTheme {
        columnMain()
    }
}

fun main() = application {

    Window(onCloseRequest = ::exitApplication,
        title = "Clean Comments Z",
        icon =  painterResource("icons/azulblanco.jpg" ),
        alwaysOnTop = false,
        resizable = true,
        undecorated = false,
        transparent = false,
        enabled = true,
        focusable = true

        ) {
        App()
    }
}



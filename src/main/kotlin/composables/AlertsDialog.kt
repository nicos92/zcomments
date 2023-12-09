package composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun miAlertDialog(title: String, descr: String, value: Boolean): Boolean {
    val open = remember { mutableStateOf(value) }

    AlertDialog(

        onDismissRequest = {
            open.value= false
        },
        title = { Text(text = title) },
        text = { Text(text = descr) },
        confirmButton = {
            TextButton(onClick = {
                open.value = false
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                open.value = false
            }) {
                Text(text = "Cancel")
            }
        }
    )
    return open.value
}
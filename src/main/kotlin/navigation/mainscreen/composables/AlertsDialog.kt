package navigation.mainscreen.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun miAlertDialog(
    title: String,
    descr: String,
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {


    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = { Text(text = title) },
            text = { Text(text = descr) },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm()

                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss()
                }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

}
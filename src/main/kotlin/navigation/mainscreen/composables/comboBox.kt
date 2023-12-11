package navigation.mainscreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun miComboBOx(mischarsets: Map<String, Any>, primerItem: String, label: String): String {
    var expanded by remember { mutableStateOf(false) }

    var selectedItem by remember { mutableStateOf(primerItem) }

    val icon = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore

    Column(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(icon, contentDescription = null)
                }
            },
            textStyle = MaterialTheme.typography.body2,
            modifier = Modifier.wrapContentWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            mischarsets.forEach { chars ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedItem = chars.key
                    }
                ) {
                    Text(
                        text = chars.key,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
            }
        }
    }
    return selectedItem
}
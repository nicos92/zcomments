@file:Suppress("UNUSED_EXPRESSION")

package composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.IOException
import javax.swing.JFileChooser

@Composable
fun miOutlinedTextField(miLabel: String, miIcon: ImageVector): String {
    var miTxt by remember { mutableStateOf("") }

    OutlinedTextField(
        value = miTxt,
        onValueChange = { miTxt = it },
        label = { Text(miLabel) },
        trailingIcon = {
            IconButton(onClick = {
                try {
                    val fc = JFileChooser()
                    val returnVAl = fc.showOpenDialog(fc)

                    if (returnVAl == JFileChooser.APPROVE_OPTION) {
                        val file = fc.selectedFile
                        miTxt = file.absolutePath
                    }
                    println("Reader: $miTxt")
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }) {
                Icon(miIcon, contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(30.dp))
            }
        },
        modifier = Modifier.fillMaxWidth(0.9f),
        textStyle = MaterialTheme.typography.body2,
        maxLines = 2

    )
    return miTxt.lowercase()
}


/**
 * muestra el la ruta de archivo de salida
 * tiene un trailing icon para mostrarlo en su carpeta
 */
@Composable
fun miTextButton(miLabel: String, miIcon: ImageVector) {

    var openDialog by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        readOnly = true,
        value = miLabel,
        onValueChange = {it},
        enabled = miLabel != "File Out",
        label = { Text("File Out") },
        textStyle = MaterialTheme.typography.body2,
        trailingIcon = {
                       IconButton(onClick = {
                           if (File(miLabel).exists()) {
                               try {
                                   val explorerExe = "explorer.exe"
                                   val command = explorerExe + " /SELECT,\"" + File(miLabel).absolutePath + "\""
                                   Runtime.getRuntime().exec(command)
                               } catch (e: IllegalArgumentException) {
                                   e.printStackTrace()
                               }catch (u:UnsupportedOperationException) {
                                   u.printStackTrace()
                               }
                           }else {
                               openDialog =true
                           }
                       },
                           enabled = miLabel!= "File Out") {
                           Icon(miIcon, contentDescription = null,
                               tint = MaterialTheme.colors.secondary,
                               modifier = Modifier.size(30.dp)
                           )
                       }
        },
        maxLines = 2,
        modifier = Modifier.fillMaxWidth(0.9f)
    )
    miAlertDialog("ERROR", "El Archivo no exite", openDialog, { openDialog = false }, { openDialog = false })
}



@Composable
fun miTextResult(txtREsult: String) {
    Text(
        text = txtREsult,
        modifier = Modifier
            .padding(16.dp)
            .wrapContentWidth(Alignment.Start),
        style = MaterialTheme.typography.body2,
        maxLines = Int.MAX_VALUE
    )
}

@Composable
fun textInfo(txtREsult: String, miColor: Color) {
    Text(
        text = txtREsult,
        modifier = Modifier
           .padding(16.dp)
           .wrapContentWidth(Alignment.Start),
        style = MaterialTheme.typography.body2,
        color = miColor,
        maxLines = Int.MAX_VALUE
    )
}
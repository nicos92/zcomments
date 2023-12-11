@file:Suppress("UNUSED_EXPRESSION")

package navigation.mainscreen.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.IOException
import javax.swing.JFileChooser

/**
 * @param miLabel ingresa el label del text field
 * @param miIcon ingresa el icono a mostrar
 * @return el nombre de archivo de entrada
 */
@Composable
fun miTextdeEntrada(miLabel: String, miIcon: ImageVector): String {
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
    return miTxt
}


/**
 * @param miLabel muestra la etiqueta, si es distitnto a 'file out' muestra el nombre de archivo de salida
 * muestra el la ruta de archivo de salida
 * tiene un trailing icon para mostrarlo en su carpeta
 */
@Composable
fun miTextdeSalida(miLabel: String, miIcon: ImageVector, focusRequester: FocusRequester) {


    var openDialog by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        readOnly = true,
        value = miLabel,
        placeholder = {
            Text("File Out")},
        onValueChange = {it},
        //enabled = miLabel != "File Out",
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
        modifier = Modifier.fillMaxWidth(0.9f).focusRequester(focusRequester)
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

/**
 * @param txtResult se usa mostrar el texto de info
 * @param miColor para cambiar deoende el texto
 */
@Composable
fun textInfo(txtResult: String, miColor: Color) {
    Text(
        text = txtResult,
        modifier = Modifier
           .padding(16.dp)
           .wrapContentWidth(Alignment.Start),
        style = MaterialTheme.typography.body2,
        color = miColor,
        maxLines = Int.MAX_VALUE
    )
}

/**
 * @param titulo ingres el nombre del titulo
 */
@Composable
fun miTitulo(titulo: String) {
    Text(
        text = titulo,
        style = MaterialTheme.typography.h5,
        color = Color.Black,
        fontFamily = FontFamily.Monospace,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    )
}
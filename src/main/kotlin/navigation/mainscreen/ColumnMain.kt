package navigation.mainscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Difference
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import buffered.leerArchivoBuffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import navigation.mainscreen.composables.*
import utils.MisCharsets.delimeters
import utils.MisCharsets.mischarsets
import utils.ResultsFile
import java.io.File


@Composable
fun columnMain() {
    val focusRequester = remember { FocusRequester() }
    var openDialog by rememberSaveable { mutableStateOf(false) }
    var confirm by rememberSaveable { mutableStateOf(false) }
    var progressBar by remember { mutableStateOf(false) }
    var fileExist by remember { mutableStateOf(false) }
    var filenameOut by remember { mutableStateOf("") }
    var results by remember {
        mutableStateOf(
            ResultsFile(0, null, 0, 0, 0, false)
        )
    }
    var mitxtResult by remember { mutableStateOf("") }
    var itemCOmbo by remember { mutableStateOf("") }
    var delimiter by remember { mutableStateOf("") }

    var miTxt = ""
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().padding(32.dp)
    ) {

        miTitulo("Clean Comments")


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                miTxt = miTextdeEntrada("File In", Icons.Default.AttachFile)
                miTooltip("*Busque o ingrese la ruta del archivo de entrada")
            }

            viewTextInfo(fileExist, miTxt)
        }
        fileExist = (miTxt.contains(".txt") || miTxt.contains(".TXT")) && File(miTxt).exists()

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            miTextdeSalida(results.fileName ?: "File Out", Icons.Default.FileOpen, focusRequester)
            miTooltip("*Ruta de archivo de salida")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            itemCOmbo =
                rowComboBox(
                    "ISO_8859_1 o ANSI", "Select Encoding", "*Seleccione la codificacion del Archivo",
                    mischarsets, 0.5f
                )
            delimiter =
                rowComboBox(
                    "//", "Select Delimeter", "*Seleccione el Delimitador de comentario",
                    delimeters, 1f
                )
        }

        Button(
            onClick = {
                mitxtResult = miTxt
                filenameOut = miTxt.replace(".txt", " - clean.txt")
                fileExist = File(filenameOut).exists()
                if (fileExist) {
                    openDialog = true
                } else {
                    confirm = true
                }
                progressBar = true
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            ),
            enabled = fileExist && !progressBar
        ) {

            Icon(
                imageVector = Icons.Default.Difference,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
            Text(
                text = "Clean",
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(8.dp)
            )
        }
        cardResult(results)
        if (progressBar) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
            )
        }

        miAlertDialog(
            "advertencia",
            "el archivo de salida ya existe\n¿quiere reemplazarlo?",
            openDialog,
            { openDialog = false; progressBar = false },
            { openDialog = false; progressBar = true; confirm = true })

        if (confirm) {
            miCoroutine {
                confirm = false

                results = leerArchivoBuffer(miTxt, filenameOut, itemCOmbo, delimiter)

                progressBar = results.enabled

                focusRequester.requestFocus()
            }

        }


    }
}

@Composable
private fun viewTextInfo(fileExist: Boolean, miTxt: String) {
    var filExist1: Boolean = fileExist
    filExist1 = (miTxt.contains(".txt") || miTxt.contains(".TXT")) && File(miTxt).exists()

    if (filExist1)
        textInfo("Archivo Valido", Color.DarkGray)
    else if (!filExist1 && miTxt.hashCode() != 0 && (miTxt.contains(".TXT") || miTxt.contains(".txt")))
        textInfo("El archivo no existe, revise sintaxis", Color.Red)
    else if ((miTxt.contains(".TXT") || !miTxt.contains(".txt")) && miTxt.hashCode() != 0)
        textInfo("El archivo no es de tipo txt", Color.Red)
}

fun miCoroutine(body: () -> Unit) {
    CoroutineScope(Dispatchers.Default).launch {
        body()
    }

}







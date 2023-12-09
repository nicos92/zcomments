package composables

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import buffered.leerArchivoBuffer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.MisCharsets.delimeters
import utils.MisCharsets.mischarsets
import utils.ResultsFile
import java.io.File


@Composable
fun columnMain() {
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

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            miImageView("drawable/fileOrange.png")
            miImageView("drawable/fileBasic.png")

            Text(
                text = "Clean Comments Z",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            )
            //miTitleImageView("drawable/title.webp")

            miImageView("drawable/azulblanco.jpg")
            miImageView("drawable/blanco.jpg")
        }

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

                miTxt = miOutlinedTextField("File In", Icons.Default.AttachFile)
                miTooltip("*Busque o ingrese la ruta del archivo de entrada")

            }

            if (fileExist)
                textInfo("Archivo Valido", Color.DarkGray)

            if (!fileExist && miTxt.hashCode() != 0 && miTxt.contains(".txt"))
                textInfo("El archivo no existe, revise sintaxis", Color.Red)
            else if (!miTxt.contains(".txt") && miTxt.hashCode() != 0)
                textInfo("El archivo no es de tipo txt", Color.Red)


        }
        fileExist = miTxt.contains(".txt") && File(miTxt).exists()

        Spacer(modifier = Modifier.padding(4.dp))


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            miTextButton(results.fileName ?: "File Out", Icons.Default.FileOpen)
            miTooltip("*Ruta de archivo de salida")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {

                itemCOmbo = miComboBOx(mischarsets, "ISO_8859_1 o ANSI", "Select Encoding")
                miTooltip("*Seleccione la codificacion del Archivo")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(1f)
            ) {

                delimiter = miComboBOx(delimeters, "//", "Delimeter")
                miTooltip("*Seleccione el Delimitador de comentario")
            }
        }


        Button(
            onClick = {

                mitxtResult = miTxt

                filenameOut = miTxt.replace(".txt", " - clean.txt")
                fileExist = File(filenameOut).exists()
                if (fileExist){
                    openDialog = true
                }else {



                    micorutina {
                        println("Hola Corutina")

                        results = leerArchivoBuffer(miTxt, filenameOut, itemCOmbo, delimiter)
                        println("results button: $results")
                        progressBar = results.enabled

                        println("chau Corutina")

                    }
                }



                progressBar = true
                println("hola progres bar ${results.enabled}")
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
            { openDialog = false; progressBar = false},
            {openDialog = false ; progressBar = true; confirm = true})

        if(confirm){
            micorutina {
                confirm = false
                println("Hola Corutina")

                results = leerArchivoBuffer(miTxt, filenameOut, itemCOmbo, delimiter)
                println("results button: $results")
                progressBar = results.enabled

                println("chau Corutina")

            }
        }








    }
}

fun micorutina( body: () -> Unit){
    CoroutineScope(Dispatchers.Default).launch {
        body()
    }
}

/*@Composable
fun buttonClean(
    mitxtResult: String,
    miTxt: String,
    results: ResultsFile,
    itemCOmbo: String,
    delimiter: String,
    progressBar: Boolean,
    fileExist: Boolean
) {
    Button(
        onClick = {

            mitxtResult = miTxt

            CoroutineScope(Dispatchers.Default).launch {

                results = leerArchivoBuffer(miTxt, itemCOmbo, delimiter)
                progressBar = results.enabled
            }

            progressBar = true
            println("hola progres bar ${results.enabled}")
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

}*/









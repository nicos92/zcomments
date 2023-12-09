package composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.ResultsFile

@Composable
fun cardResult(results: ResultsFile) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.padding(10.dp).background(Color.LightGray, RoundedCornerShape(20.dp)),
            elevation = 10.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                miTextResult(
                    "Lineas de Archivo de Entrada: ${results.lineFile}\n" +
                            "Lineas de Archivo de Salida: ${results.lineFile - results.linesMod}"
                )
                miTextResult(
                    "Lineas Vacias: ${results.lineVacia}\n" +
                            "Lineas con Delimitador: ${results.lineDelimeter}"
                )
            }
        }
    }
}
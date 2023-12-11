package navigation.mainscreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import utils.MisCharsets
import java.nio.charset.Charset

@Composable
fun rowComboBox(primerItem: String, label: String, miToolTip: String, miMap: Map<String, Any>, widthRow:Float): String {
    var itemCombo = ""
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(widthRow)
    ){
         itemCombo = miComboBOx(miMap, primerItem, label)
        miTooltip(miToolTip)
    }
    return itemCombo
}
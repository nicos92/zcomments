package composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun miTooltip(toolTip: String) {
    TooltipArea(tooltip = {
        Box(
            modifier = Modifier.padding(10.dp).background(Color.LightGray, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                toolTip,
                modifier = Modifier.padding(4.dp)
            )
        }
    }) {

        IconButton(
            onClick = {/*TODO*/ },

            ) {
            Icon(
                Icons.Default.Help, contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

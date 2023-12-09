package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun miImageView(nameIMG: String) {
    Image(
        painter = painterResource(nameIMG),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
    )

}
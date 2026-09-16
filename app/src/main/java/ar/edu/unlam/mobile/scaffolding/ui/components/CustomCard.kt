package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

data class CardData(
    val title: String,
    val text: String,
    val image: String,
    val likes: Int,
    val commentsAmount: Int,
)

@Composable
fun CustomCard(
    data: CardData,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
            modifier.size(300.dp, 400.dp),
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                Row {
                    Text(data.title, fontSize = 32.sp)
                }
                Row {
                    Text(data.text, fontSize = 16.sp)
                }
                AsyncImage(
                    model = data.image,
                    contentDescription = "Android Picture",
                )
                Row {
                    Text("❤️ ${data.likes}", fontSize = 16.sp)
                    Text("💬 ${data.commentsAmount}", fontSize = 16.sp)
                }
            }
        }
    }
}

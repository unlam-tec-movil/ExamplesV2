package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.RealAndroid

@Composable
fun AndroidForm(
    onSubmit: (result: RealAndroid) -> Unit,
    modifier: Modifier = Modifier,
) {
    val paddingModifier = Modifier.padding(10.dp)
    var androidName by remember { mutableStateOf(TextFieldValue("")) }
    var androidDescription by remember { mutableStateOf(TextFieldValue("")) }
    var androidPicture by remember { mutableStateOf(TextFieldValue("")) }

    Card(modifier = paddingModifier) {
        Row(modifier = paddingModifier) {
            Column {
                TextField(
                    value = androidName,
                    onValueChange = { androidName = it },
                    label = { Text("Name") },
                )
            }
        }
        Row(modifier = paddingModifier) {
            Column {
                TextField(
                    value = androidDescription,
                    onValueChange = { androidDescription = it },
                    label = { Text("Description") },
                )
            }
        }
        Row(modifier = paddingModifier) {
            Column {
                TextField(
                    value = androidPicture,
                    onValueChange = { androidPicture = it },
                    label = { Text("Image URL") },
                )
            }
        }
        Row(modifier = paddingModifier.align(alignment = Alignment.End)) {
            Button(
                onClick = {
                    onSubmit(
                        RealAndroid(
                            id = null,
                            name = androidName.text,
                            description = androidDescription.text,
                            picture = androidPicture.text,
                        ),
                    )
                },
                content = { Text(text = "Create") },
            )
        }
    }
}

@Preview
@Composable
private fun AndroidFormPreview() {
    AndroidForm(onSubmit = {})
}

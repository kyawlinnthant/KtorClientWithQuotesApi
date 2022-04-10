package klt.mdy.kotlinserializationtest.ui.screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuotesError(
    modifier : Modifier = Modifier,
    error : String,
){
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = error,
        style = MaterialTheme.typography.h4
    )
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    Surface {
        QuotesError(error = "This is the error")
    }
}
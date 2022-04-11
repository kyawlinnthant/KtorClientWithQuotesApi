package klt.mdy.kotlinserializationtest.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import klt.mdy.kotlinserializationtest.model.Quote

@Composable
fun QuotesSuccess(
    modifier: Modifier = Modifier,
    quote: Quote
) {
    BoxWithConstraints {
        Box(modifier =modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        )
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = quote.data,
                style = MaterialTheme.typography.h4
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        end = 32.dp
                    ),
                text = quote.writer,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic,

                )
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp
                )
            ) {
                items(quote.tags) { item ->
                    Text(
                        text = "#$item",
                        style = MaterialTheme.typography.overline,
                        color = MaterialTheme.colors.secondary,
                    )
                }
            }
            Spacer(modifier = modifier.height(16.dp))

        }
    }

}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    Surface {
        QuotesSuccess(
            quote = Quote(
                id = 1,
                data = "This is the quote from server that we want to display in screen",
                writer = "Writer",
                tags = listOf(
                    "Love",
                    "Sex",
                    "Drug",
                    "Eat",
                    "Sleep"
                )
            )
        )
    }
}
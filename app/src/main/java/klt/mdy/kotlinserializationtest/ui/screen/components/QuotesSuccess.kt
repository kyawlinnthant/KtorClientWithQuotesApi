package klt.mdy.kotlinserializationtest.ui.screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    /* BoxWithConstraints {
         Box(modifier = modifier
             .fillMaxSize()
             .verticalScroll(rememberScrollState())
         )*/
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = quote.text,
                style = MaterialTheme.typography.h4
            )
        }
        item {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        end = 32.dp
                    ),
                text = quote.author,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic,

                )
        }
        item {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                text = "#${quote.category}",
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.secondary,
            )
        }
        item {
            Spacer(modifier = modifier.height(32.dp))
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
                text = "This is the quote from server that we want to display in screen",
                author = "Writer",
                category = "Love"
            )
        )
    }
}
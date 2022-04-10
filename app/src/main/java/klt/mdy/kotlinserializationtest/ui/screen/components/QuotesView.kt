package klt.mdy.kotlinserializationtest.ui.screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import klt.mdy.kotlinserializationtest.ui.QuotesViewModel
import klt.mdy.kotlinserializationtest.ui.screen.udf.Actions
import klt.mdy.kotlinserializationtest.ui.screen.udf.Events
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuotesView() {
    val scaffoldState = rememberScaffoldState()
    val vm: QuotesViewModel = hiltViewModel()
    val state = vm.states.value

    LaunchedEffect(key1 = true) {
        vm.events.collectLatest {
            when (it) {
                is Events.ShowSnack -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message
                    )
                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.surface,
                elevation = 1.dp,
                content = {
                    Text(text = "New quotes and have fun")
                },
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    vm.onAction(
                        action = Actions.ClickNext
                    )
                },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.surface,
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "next quote"
                )
            }
        },
        content = {
            QuotesContent(quote = state.quote)
        }
    )
}
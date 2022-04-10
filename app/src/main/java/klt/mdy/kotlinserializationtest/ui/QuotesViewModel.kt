package klt.mdy.kotlinserializationtest.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import klt.mdy.kotlinserializationtest.common.Resource
import klt.mdy.kotlinserializationtest.model.Quote
import klt.mdy.kotlinserializationtest.repo.QuotesRepository
import klt.mdy.kotlinserializationtest.ui.screen.udf.Actions
import klt.mdy.kotlinserializationtest.ui.screen.udf.Events
import klt.mdy.kotlinserializationtest.ui.screen.udf.States
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repo: QuotesRepository
) : ViewModel() {

    private val _states = mutableStateOf(States())
    val states: State<States> get() = _states

    private val _events = MutableSharedFlow<Events>()
    val events: SharedFlow<Events> get() = _events

    init {
        fetchNewRandomQuote()
    }

    fun onAction(action: Actions) {
        when (action) {
            Actions.ClickNext -> {
                fetchNewRandomQuote()
            }
        }
    }

    private fun fetchNewRandomQuote() {
        viewModelScope.launch {
            repo.fetchNewQuote().collect {
                when (it) {
                    is Resource.Error -> {
                        _events.emit(
                            value = Events.ShowSnack(
                                message = it.message ?: "Error"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        //todo : ui loading states
                        _events.emit(
                            value = Events.ShowSnack(
                                message = it.message ?: "Loading"
                            )
                        )
                    }
                    is Resource.Success -> {
                        _states.value = states.value.copy(
                            quote = it.data ?: Quote()
                        )
                    }
                }
            }
        }
    }

}
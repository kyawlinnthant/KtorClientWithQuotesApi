package klt.mdy.kotlinserializationtest.ui.screen.udf

sealed class Events{
    data class ShowSnack(val message : String) : Events()
}

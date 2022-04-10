package klt.mdy.kotlinserializationtest.ui.screen.udf

import klt.mdy.kotlinserializationtest.common.Resource
import klt.mdy.kotlinserializationtest.model.Quote

data class States(
    val quote: Resource<Quote> = Resource.Loading()
)

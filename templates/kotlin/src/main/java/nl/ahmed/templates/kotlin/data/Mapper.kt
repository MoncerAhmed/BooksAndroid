package nl.ahmed.templates.kotlin.data

fun interface Mapper<I, O> : (I) -> O

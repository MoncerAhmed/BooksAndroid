package nl.ahmed.common.kotlin.templates

fun interface Mapper<I, O> : (I) -> O

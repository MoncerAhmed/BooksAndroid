package nl.ahmed.templates.kotlin.data

fun interface Mapper<I, O> : (I) -> O

fun interface MapperWithArgs<I, O> {
    operator fun invoke(input: I, vararg args: Any): O

    data class MapperArgsException(override val message: String) : Throwable()
}

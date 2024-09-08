package nl.ahmed.features.details.data.mappers

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.features.details.domain.api.models.BookDetails
import nl.ahmed.templates.kotlin.data.MapperWithArgs

@FragmentScope
internal class BookDalToBookDetailsMapper @Inject constructor() :
    MapperWithArgs<BookData, BookDetails> {
    override fun invoke(input: BookData, vararg args: Any): BookDetails {
        val isFavorite = getIsFavoriteArg(args)
        return BookDetails(
            id = BookDetails.Id(input.id.value),
            coverUrl = input.coverUrl,
            author = input.author,
            title = input.title,
            createdAt = input.createdAt,
            description = input.description,
            summary = input.summary,
            reads = input.reads,
            reviews = input.reviews,
            isFavorite = isFavorite
        )
    }

    private fun getIsFavoriteArg(args: Array<out Any>): Boolean {
        validateArgs(args)
        return args[0] as Boolean
    }

    private fun validateArgs(args: Array<out Any>) {
        if (args.isEmpty() || args.size > 1 || args[0] !is Boolean)
            throw MapperWithArgs.MapperArgsException("BookDalToBookDetailsMapper only accepts one argument of type Boolean [isFavorite: Boolean]")
    }
}

package nl.ahmed.templates.kotlin.domain

interface UseCase<I, O> {
    suspend operator fun invoke(input: I): O
}

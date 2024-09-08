package nl.ahmed.templates.kotlin.domain

interface NoArgUseCase<O> {
    suspend operator fun invoke(): O
}

package nl.ahmed.common.kotlin.templates

sealed interface Model {
    interface Domain : Model
    interface Dto : Model
    interface Entity : Model {
        val id: Id

        interface Id {
            val value: String
        }
    }
}

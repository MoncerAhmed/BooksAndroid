package nl.ahmed.templates.kotlin.data

sealed interface Model {
    interface Data : Model
    interface Dto : Model
    interface Entity : Model {
        val id: Id

        interface Id {
            val value: String
        }
    }
}

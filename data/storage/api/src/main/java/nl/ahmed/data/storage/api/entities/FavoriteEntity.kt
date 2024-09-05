package nl.ahmed.data.storage.api.entities

import nl.ahmed.templates.kotlin.Model

interface FavoriteEntity : Model.Entity {
    override val id: BookEntity.Id

    fun copyEntity(id: BookEntity.Id = this.id) : FavoriteEntity

    interface Factory {
        fun create(id: BookEntity.Id): FavoriteEntity
    }
}

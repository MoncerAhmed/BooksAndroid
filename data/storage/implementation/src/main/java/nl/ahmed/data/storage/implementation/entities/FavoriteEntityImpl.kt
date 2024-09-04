package nl.ahmed.data.storage.implementation.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.FavoriteEntity

@Entity(
    tableName = "favorites",
    foreignKeys = [
        ForeignKey(
            entity = BookEntityImpl::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onUpdate = ForeignKey.NO_ACTION,
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
internal data class FavoriteEntityImpl(
    @PrimaryKey override val id: BookEntity.Id
) : FavoriteEntity {
    override fun copyEntity(id: BookEntity.Id) : FavoriteEntity = copy(id = id)

    class FactoryImpl : FavoriteEntity.Factory {
        override fun create(id: BookEntity.Id): FavoriteEntity = FavoriteEntityImpl(
            id = id
        )
    }
}

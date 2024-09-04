package nl.ahmed.data.storage.implementation

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nl.ahmed.common.android.converters.room.LocalDateTimeConverter
import nl.ahmed.data.storage.api.BookDatabase
import nl.ahmed.data.storage.api.daos.FavoritesDao
import nl.ahmed.data.storage.implementation.daos.BooksDaoImpl
import nl.ahmed.data.storage.implementation.daos.FavoriteDaoImpl
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl
import nl.ahmed.data.storage.implementation.entities.FavoriteEntityImpl

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        BookEntityImpl::class,
        FavoriteEntityImpl::class
    ],
)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class BookDatabaseImpl : RoomDatabase(), BookDatabase {
    abstract override fun booksDao(): BooksDaoImpl
    abstract override fun favoritesDao(): FavoriteDaoImpl
}

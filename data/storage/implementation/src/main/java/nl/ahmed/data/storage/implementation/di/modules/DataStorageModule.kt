package nl.ahmed.data.storage.implementation.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.data.storage.api.BookDatabase
import nl.ahmed.data.storage.implementation.BookDatabaseImpl

@Module(includes = [BooksModule::class])
internal class DataStorageModule {

    @Singleton
    @Provides
    fun providesDatabase(
        context: Context
    ): BookDatabase = Room
        .databaseBuilder(
            context = context,
            BookDatabaseImpl::class.java,
            name = "book_database"
        )
        .build()
}

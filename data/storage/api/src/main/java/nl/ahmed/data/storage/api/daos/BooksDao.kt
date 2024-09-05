package nl.ahmed.data.storage.api.daos

import nl.ahmed.templates.kotlin.Dao
import nl.ahmed.data.storage.api.entities.BookEntity

interface BooksDao : Dao.Query<BookEntity>, Dao.Update<BookEntity>, Dao.Delete<BookEntity>

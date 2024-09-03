package nl.ahmed.data.storage.api.daos

import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.data.storage.api.entities.BookEntity

interface BooksDao : Dao.Query<BookEntity>, Dao.Insert<BookEntity>, Dao.Delete<BookEntity>

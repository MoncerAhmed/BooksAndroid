package nl.ahmed.common.kotlin.templates

sealed interface Dao<E: Model.Entity> {
    interface Insert<E: Model.Entity> : Dao<E> {
        suspend fun insert(items: List<E>)
        suspend fun insert(item: E)
    }

    interface Query<E: Model.Entity> : Dao<E> {
        suspend fun getAll(): List<E>
        suspend fun get(id: Model.Entity.Id): E
    }

    interface Delete<E: Model.Entity> : Dao<E> {
        suspend fun delete(vararg items: E)
        suspend fun deleteAll()
    }
}

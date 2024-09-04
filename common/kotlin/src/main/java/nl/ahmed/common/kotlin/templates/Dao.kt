package nl.ahmed.common.kotlin.templates

sealed interface Dao {
    interface Insert<E: Model.Entity> : Dao {
        suspend fun insert(items: List<E>)
        suspend fun insert(item: E)
    }

    interface Query<E: Model.Entity> : Dao {
        suspend fun getAll(): List<E>
        suspend fun get(id: Model.Entity.Id): E
    }

    interface Delete<E: Model.Entity> : Dao {
        suspend fun delete(vararg items: E)
        suspend fun deleteAll()
    }

    interface Update<E: Model.Entity> : Dao {
        suspend fun updateAll(items: List<E>)
    }
}

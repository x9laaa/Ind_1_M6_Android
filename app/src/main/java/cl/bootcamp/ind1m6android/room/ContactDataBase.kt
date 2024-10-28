package cl.bootcamp.ind1m6android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.ind1m6android.model.Contact


@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
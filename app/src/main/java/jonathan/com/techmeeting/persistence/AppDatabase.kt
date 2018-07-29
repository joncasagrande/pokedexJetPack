package jonathan.com.techmeeting.persistence

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context


@Database(entities = arrayOf(Pokemon::class), version = 1)


abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
}

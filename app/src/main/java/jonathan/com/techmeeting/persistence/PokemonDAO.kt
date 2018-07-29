package jonathan.com.techmeeting.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
public interface PokemonDAO{

    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id IN (:id)")
    fun loadAllByIds(id: Int): Pokemon

    @Query("SELECT * FROM pokemon WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String): Pokemon

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokemon: Pokemon)

}
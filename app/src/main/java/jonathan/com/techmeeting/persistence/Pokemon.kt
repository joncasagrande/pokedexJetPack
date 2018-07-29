package jonathan.com.techmeeting.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Pokemon(@PrimaryKey val id: Int, val image: String, val name: String, val description: String)

package ee.artjom.requesttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val status: RequestStatus = RequestStatus.NEW,
    val createdAt: Long = System.currentTimeMillis()
)
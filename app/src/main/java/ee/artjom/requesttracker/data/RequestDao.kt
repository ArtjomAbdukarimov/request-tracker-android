package ee.artjom.requesttracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RequestDao {

    @Query("SELECT * FROM requests ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<RequestEntity>>

    @Query("SELECT * FROM requests WHERE status = :status ORDER BY createdAt DESC")
    fun observeByStatus(status: RequestStatus): Flow<List<RequestEntity>>

    @Insert
    suspend fun insert(item: RequestEntity)

    @Query("UPDATE requests SET status = :newStatus WHERE id = :id")
    suspend fun updateStatus(id: Long, newStatus: RequestStatus)
}
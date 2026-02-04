package ee.artjom.requesttracker.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStatus(status: RequestStatus): String = status.name

    @TypeConverter
    fun toStatus(value: String): RequestStatus = RequestStatus.valueOf(value)
}
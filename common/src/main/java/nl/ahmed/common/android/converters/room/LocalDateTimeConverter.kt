package nl.ahmed.common.android.converters.room

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {
    @TypeConverter
    fun fromString(value: String): ZonedDateTime {
        return ZonedDateTime
            .parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    @TypeConverter
    fun toString(localDateTime: ZonedDateTime): String {
        return localDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }
}

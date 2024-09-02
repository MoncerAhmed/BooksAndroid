package nl.ahmed.core.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    override fun write(writer: JsonWriter?, value: LocalDateTime?) {
        writer?.value(value?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    }

    override fun read(reader: JsonReader?): LocalDateTime {
        return LocalDateTime.parse(
            reader?.nextString(),
            DateTimeFormatter.ISO_ZONED_DATE_TIME
        )
    }
}

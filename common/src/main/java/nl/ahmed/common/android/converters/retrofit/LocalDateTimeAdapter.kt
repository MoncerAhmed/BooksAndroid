package nl.ahmed.common.android.converters.retrofit

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeAdapter : TypeAdapter<ZonedDateTime>() {
    override fun write(writer: JsonWriter?, value: ZonedDateTime?) {
        writer?.value(value?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    }

    override fun read(reader: JsonReader?): ZonedDateTime {
        return ZonedDateTime.parse(
            reader?.nextString(),
            DateTimeFormatter.ISO_ZONED_DATE_TIME
        )
    }
}

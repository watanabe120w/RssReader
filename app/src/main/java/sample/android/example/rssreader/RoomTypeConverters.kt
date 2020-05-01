package sample.android.example.rssreader

import androidx.room.TypeConverter
import java.util.*
import kotlin.time.milliseconds

/**
 * Room用変換処理
 */
class RoomTypeConverters {
    /**
     *  Date型 -> Long型変換
     *
     * @param date
     * @return
     */
    @TypeConverter
    fun fromDateToLong(date: Date): Long = date.time

    /**
     * Long型 -> Date型
     *
     * @param long
     * @return
     */
    @TypeConverter
    fun fromLongToDate(long: Long): Date = Date(long)
}
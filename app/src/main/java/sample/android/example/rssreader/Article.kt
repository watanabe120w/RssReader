package sample.android.example.rssreader

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = "articles"
)
//data class Article(val title: String, val link: String, val pubDate: Date)
data class Article(
    @PrimaryKey
    @ColumnInfo val title: String,
    @ColumnInfo val link: String,
    @ColumnInfo val pubDate: Date
)
{
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var articleId: Long = 0

    @SuppressLint("SimpleDateFormat")
    fun pubDateFormattedString():String {
        val dateFormat = SimpleDateFormat("yyyy年M月d日 H時m分")
        return dateFormat.format(pubDate)
        //val ret = SimpleDateFormat(RssReaderApplication().getString(R.string.pubDate)).format(pubDate)
        //return ret
    }
}
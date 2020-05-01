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
/**
 * 記事クラス(Room Entity)
 */
data class Article(
    /**
     * タイトル(プライマリーキー)
     */
    @PrimaryKey
    @ColumnInfo val title: String,

    /**
     * リンクURL
     */
    @ColumnInfo val link: String,

    /**
     * 発行日時
     */
    @ColumnInfo val pubDate: Date,

    /**
     * 画像URL
     */
    @ColumnInfo val imageUrl: String
)

{
    /**
     * 発行日時を指定のフォーマットで出力する処理
     *
     * @return フォーマット適用後の発行日時
     */
    @SuppressLint("SimpleDateFormat")
    fun pubDateFormattedString():String = SimpleDateFormat(DISPLAY_PUBLISH_DATE_FORMAT).format(pubDate)
}
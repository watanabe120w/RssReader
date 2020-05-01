package sample.android.example.rssreader

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY pubDate ASC")
    fun getArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article:Article): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( articles: List<Article>)

    @Delete
    suspend fun deleteArticle(article:Article)
}
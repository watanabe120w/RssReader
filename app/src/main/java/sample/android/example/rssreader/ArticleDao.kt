package sample.android.example.rssreader

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * 記事取得DAO
 */
@Dao
interface ArticleDao {
    /**
     * 記事取得処理
     *
     * @return 取得した記事(LiveDataの記事のリストで降順)
     */
    @Query("SELECT * FROM articles ORDER BY pubDate DESC")
    fun getArticles(): LiveData<List<Article>>

    /**
     * 1件の記事のInsert処理
     *
     * @param article 記事
     * @return Insertに成功したデータのrawId
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article:Article): Long

    /**
     * 複数の記事のInsert処理
     *
     * @param articles 記事のリスト
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( articles: List<Article>)

    /**
     * 指定した記事の削除処理
     *
     * @param article 削除する記事
     */
    @Delete
    suspend fun deleteArticle(article:Article)
}
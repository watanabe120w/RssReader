package sample.android.example.rssreader

import androidx.lifecycle.LiveData

class RssReaderRepository private constructor(
    private val articleDao: ArticleDao
){
    fun getArticleData() = articleDao.getArticles()

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: RssReaderRepository? = null

        fun getInstance(articleDao: ArticleDao) =
            instance ?: synchronized(this) {
                instance ?: RssReaderRepository(articleDao).also { instance = it }
            }
    }
}
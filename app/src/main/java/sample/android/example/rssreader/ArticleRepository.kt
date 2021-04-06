package sample.android.example.rssreader

/**
 * 記事取得処理リポジトリ
 *
 * @property articleDao 記事取得DAO
 */
class ArticleRepository private constructor(
    private val articleDao: ArticleDao
){
    /**
     * 記事データ取得処理
     *
     */
    fun getArticleData() = articleDao.getArticles()

    companion object {

        // For Singleton instantiation
        /**
         * このクラスのインスタンス
         */
        @Volatile
        private var instance: ArticleRepository? = null

        /**
         * インスタンス取得処理
         *　(なければ排他制御して作って、あれば、すでにあるやつを使う。
         * @param articleDao
         */
        fun getInstance(articleDao: ArticleDao) =
            instance ?: synchronized(this) {
                instance ?: ArticleRepository(articleDao).also { instance = it }
            }
    }
}
package sample.android.example.rssreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * Main画面で使うViewModelクラス
 *
 * @constructor
 * 初期化処理　
 *
 * @param articleRepository 記事取得リポジトリのインスタンス
 */
class MainViewModel internal constructor(
    articleRepository: ArticleRepository
) : ViewModel() {
    /**
     * 記事のリスト(LiveData)
     */
    val articles: LiveData<List<Article>> = articleRepository.getArticleData()
}


package sample.android.example.rssreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * メイン画面のViewModel作成処理
 * ※引数があるものは、この形式で作成が必要
 * @property repository 記事取得処理リポジトリ
 */
class MainViewModelFactory (
    private val repository: ArticleRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
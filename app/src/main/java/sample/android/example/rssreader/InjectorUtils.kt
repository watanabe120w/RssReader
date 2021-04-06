package sample.android.example.rssreader

import android.content.Context

/**
 *  依存性注入用ユーティリティークラス
 */
object InjectorUtils {
    /**
     * 記事リポジトリ取得処理
     *
     * @param context
     * @return ArticleRepositoryインスタンス
     */
    private fun getArticleRepository(context: Context): ArticleRepository {
        return ArticleRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).articleDao()
        )
    }

    /**
     * メインViewModel作成処理
     *
     * @param context
     * @return MainViewModelFactoryインスタンス
     */
    fun providerMainViewModelFactory(
        context: Context
    ): MainViewModelFactory {
        val repository = getArticleRepository(context)
        return MainViewModelFactory(repository)
    }
}
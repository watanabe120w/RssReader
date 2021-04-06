package sample.android.example.rssreader

import android.content.Context
import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sample.android.example.rssreader.databinding.ActivityMainBinding
import sample.android.example.rssreader.databinding.GridArticleCellBinding

class ArticlesAdapter()
    : ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(
    ArticleDiffCallback()
) {
    // 新しくViewを作る
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(GridArticleCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    // Viewに表示すべき値を設定する
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    /**
     * ビューホルダークラス
     */
    class ArticleViewHolder(
        private val binding: GridArticleCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                // 記事をタップしたときの処理
                val intent = CustomTabsIntent.Builder().build()
                binding.article?.let { article ->
                    // 記事のリンク先URLに遷移
                    intent.launchUrl(binding.root.context, Uri.parse(article.link))
                }

            }
        }

        /**
         * バインド処理
         * 記事をUIに関連づける
         *
         * @param item 記事データ
         */
        fun bind(item: Article) {
            binding.apply {
                article = item
                executePendingBindings()
            }
        }
    }
}

/**
 * ListAdapter用 更新を検出する用のクラス
 *
 */
private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

    /**
     * 項目が同じかどうかの判定処理
     *
     * @param oldItem 古い項目
     * @param newItem 新しい項目
     * @return　true: 同じ false: 違う
     */
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }

    /**
     * 内容が同じかどうかの判定処理
     *
     * @param oldItem 古い項目
     * @param newItem 新しい項目
     * @return true: 同じ false: 違う
     */
    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }
}
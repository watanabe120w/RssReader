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

class ArticlesAdapter(
//    val inflater: LayoutInflater
//    private val context: Context
//    private val articles: List<Article>,
//    private val onArticleClicked: (Article) -> Unit)
)
//   : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {
    : ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(
    ArticleDiffCallback()
) {

    //private val inflater = LayoutInflater.from(context)

    //override fun getItemCount() = articles.size

    // 新しくViewを作る
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        // Viewを生成する
//        val view = inflater.inflate(R.layout.grid_article_cell, parent, false)
//        // ViewHolderを生成する
//        val viewHolder = ArticleViewHolder(view)

//        // Viewタップ時の処理
//        view.setOnClickListener {
//            // タップされた記事の位置
//            val position = viewHolder.adapterPosition
//            // タップされた位置に応じた記事
//            val article = articles[position]
//            // コールバックを呼ぶ
//            onArticleClicked(article)
//        }

        return ArticleViewHolder(GridArticleCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    // Viewに表示すべき値を設定する
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
//        // アダプター中の位置に応じた記事を得る
//        val article = articles[position]
//        // 記事のタイトルを設定する
//        holder.title.text = article.title
//        // 記事の発行日付を設定する
//        holder.pubDate.text = context.getString(R.string.pubDate, article.pubDate)
//        holder.bindTo(getItem(position))
        val article = getItem(position)
        //(holder as ArticleViewHolder).bind(article)
        holder.bind(article)
    }

    // ビューホルダー
    class ArticleViewHolder(
        private val binding: GridArticleCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                // 記事をタップしたときの処理
                val intent = CustomTabsIntent.Builder().build()
                binding.article?.let { article ->
                    intent.launchUrl(binding.root.context, Uri.parse(article.link))
                }

            }
        }

        fun bind(item: Article) {
            binding.apply {
                article = item
                executePendingBindings()
            }
        }
    }
}

private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }
}
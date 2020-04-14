package sample.android.example.rssreader

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Loader
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.loader.app.LoaderManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit


abstract class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Rss>,
    android.app.LoaderManager.LoaderCallbacks<Any> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ローダーを呼び出す
        loaderManager.initLoader(1, null, this)


        // 通知チャンネルを作成する
        createChannel(this)

        // 定期的に新しい記事が無いかをチェックするジョブ
        val fetchJob = JobInfo.Builder(1, ComponentName(this, PollingJob::class.java))
            .setPeriodic(TimeUnit.HOURS.toMillis(6)) // 6時間ごとに実行
            .setPersisted(true) // 端末を再起動しても有効
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // ネットワーク接続されていること
            .build()

        // ジョブを登録する
        getSystemService(JobScheduler::class.java).schedule(fetchJob)
    }

    // ローダーが要求された時に呼ばれる
    override fun onCreateLoader(id: Int, args: Bundle?) = RssLoader(this)

    // ローダーがリセットされた時に呼ばれる
    override fun onLoaderReset(loader: androidx.loader.content.Loader<Rss>) {
        // 特に何もしない
    }

    // ローダーで行った処理が終了した時に呼ばれる
    override fun onLoadFinished(loader: androidx.loader.content.Loader<Rss>, data: Rss?) {
        // 処理結果がnullじゃない場合
        if (data != null) {

            // RecyclerViewをレイアウトから探す
            val recyclerView = findViewById<RecyclerView>(R.id.articles)

            // RSSの記事一覧のアダプター
            val adapter = ArticlesAdapter(this, data.articles) { article ->
                // 記事をタップしたときの処理
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(this, Uri.parse(article.link))
            }
            recyclerView.adapter = adapter

            // グリッド表示するレイアウトマネージャ
            val layoutManager = GridLayoutManager(this, 2)

            recyclerView.layoutManager = layoutManager
        }
    }
}

package sample.android.example.rssreader

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class RssWorker(
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result = coroutineScope {
        try {
            // RSSをダウンロード
            downloadRss()?.also { rss ->
                // 必要があれば通知を実施
                notifyIfNeeded(rss)
                // Databaseに書き込む
                val database = AppDatabase.getInstance(applicationContext)
                database.articleDao().insertAll(rss.articles)
                val articles= database.articleDao().getArticles()
                //Log.d(TAG,articles.value.toString())
            } ?:run {
                Result.failure()
            }
            Result.Success()
        } catch(ex: Exception) {
            Log.e(TAG, "Error Rss Worker")
            Result.failure()
        }
    }
    private fun downloadRss(): Rss?{
        // RSSをダウンロードする
        return httpGet("https://www.sbbit.jp/rss/HotTopics.rss")?.let { response ->
            // RSSオブジェクトにパースする
            parseRss(response)
        }

    }
    private fun notifyIfNeeded(rss: Rss) {
        // プリファレンス
        val prefs = applicationContext.getSharedPreferences("pref_polling", Context.MODE_PRIVATE)

        // 前回更新されたときの時間。保存されていない場合は0を返す
        val lastFetchTime = prefs.getLong("last_publish_time", 0L)

        // 記事が一度は取得済みで、更新されている場合
        //if (lastFetchTime > 0 && lastFetchTime < rss.pubDate.time) {
            // 通知する
            notifyUpdate(applicationContext)
        //}

        // 取得時間を保存する
        prefs.edit().putLong("last_publish_time", rss.pubDate.time).apply()
    }
    companion object {
        private val TAG = RssWorker::class.java.simpleName
    }
}
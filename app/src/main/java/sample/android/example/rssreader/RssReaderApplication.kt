package sample.android.example.rssreader

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class RssReaderApplication(): Application() {
    override fun onCreate() {
        super.onCreate()

        // 通知チャンネルを作成する
        createChannel(this)

        // バックグラウンド処理の実行(WorkManager)
        val gettingRssRequest = PeriodicWorkRequestBuilder<RssWorker>(RSS_REQUEST_INTERVAL, TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(gettingRssRequest)
    }
}
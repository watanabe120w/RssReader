package sample.android.example.rssreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel internal constructor(
    rssReaderRepository: RssReaderRepository
) : ViewModel() {
    val articles: LiveData<List<Article>> = rssReaderRepository.getArticleData()
}


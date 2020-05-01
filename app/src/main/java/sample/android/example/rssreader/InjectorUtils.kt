package sample.android.example.rssreader

import android.content.Context

object InjectorUtils {
    private fun getRssReaderRepository(context: Context): RssReaderRepository {
        return RssReaderRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).articleDao()
        )
    }

    fun providerMainViewModelFactory(
        context: Context
    ): MainViewModelFactory {
        val repository = getRssReaderRepository(context)
        return MainViewModelFactory(repository)
    }
}
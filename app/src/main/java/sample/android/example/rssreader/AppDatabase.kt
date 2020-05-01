package sample.android.example.rssreader

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * データベースクラス(Room使用)
 */
@Database( entities = [Article::class],
    version = 1,
    exportSchema = true)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun articleDao(): ArticleDao

    companion object {
        /**
         * AppDatabaseクラスのインスタンス(Singleton)
         */
        @Volatile private var instance: AppDatabase? = null

        /**
         * インスタンス取得処理(あれば今あるものを使い、なければ作る)
         *
         * @param context アプリケーションコンテキスト
         * @return AppDatabaseクラスのインスタンス
         */
        fun getInstance(context: Context): AppDatabase {
            return  instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        /**
         * データベース作成処理
         *
         * @param context
         * @return AppDatabaseクラスのインスタンス
         */
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DBNAME)
                .build()
        }
    }
}
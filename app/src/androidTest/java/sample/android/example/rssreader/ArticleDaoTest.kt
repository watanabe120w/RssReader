package sample.android.example.rssreader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sample.android.example.rssreader.Utilities.getValue
import java.util.*

//@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
    // TODO なぜかSunflowerと同じにしたらうまくいかなかったので、
    private lateinit var database: AppDatabase
    private lateinit var articleDao: ArticleDao
    private lateinit var articleA: Article
    private lateinit var articleB: Article
    private lateinit var articleC: Article

    // LiveDataのユニットテストを行うのに必要！
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * 前処理
     */
    @Before
    fun setUp() {
        runBlocking {
            articleA = Article("test1","https://www.yahoo.co.jp", Date())
            delay(1000) // Dateを違う値にするために少しインターバルを空ける
            articleB = Article("test2","https://www.google.co.jp",Date())
            delay(1000)
            articleC = Article("test3","https://shopping.yahoo.co.jp/",Date())

            val context =
                InstrumentationRegistry.getInstrumentation().targetContext    // テスト用コンテキストの取得
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
            articleDao = database.articleDao()
            // Insertする順番は変える
            articleDao.insertArticle(articleB)
            articleDao.insertArticle(articleC)
            articleDao.insertArticle(articleA)
        }
    }

    /**
     * 後処理
     */
    @After fun tearDown() {
        database.close()
    }

    /**
     * 記事取得処理テスト
     */
    @Test fun testGetArticles() {
        val articleList = getValue(articleDao.getArticles())
        assertThat(articleList.count(),equalTo(3))  // 3つInsertしたので、3つ取れるはず
        // 取得は時間の新しい順になる
        assertThat(articleList[0], equalTo(articleC))
        assertThat(articleList[1], equalTo(articleB))
        assertThat(articleList[2], equalTo(articleA))
    }
}
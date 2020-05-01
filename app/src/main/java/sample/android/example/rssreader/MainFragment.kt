package sample.android.example.rssreader

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_main.*
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sample.android.example.rssreader.databinding.FragmentMainBinding

/**
 * メイン画面クラス(Fragment)
 *
 */
class MainFragment: Fragment() {
    /**
     * ViewModel
     */
    private val viewModel: MainViewModel by viewModels() {
        InjectorUtils.providerMainViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // UIにアダプタとレイアウトをバインディングする
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val adapter = ArticlesAdapter()
        //val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.apply {
            this.articles.adapter = adapter
            //this.articles.layoutManager = layoutManager
        }
        // 購読処理
        subscribeUi(adapter)
        return binding.root
    }

    /**
     * 記事一覧を購読する処理
     * 記事が更新されたら、RecyclerViewを更新する
     * @param adapter RecyclerViewのアダプタ
     */
    private fun subscribeUi(adapter: ArticlesAdapter) {
        viewModel.articles.observe(viewLifecycleOwner) { articleList ->
            adapter.submitList(articleList)
        }
    }
    companion object {
        /**
         * ログ用タグ
         */
        private val TAG = MainFragment::class.java.simpleName
    }
}
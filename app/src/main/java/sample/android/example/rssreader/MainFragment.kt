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

class MainFragment: Fragment() {

//    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels() {
        InjectorUtils.providerMainViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val adapter = ArticlesAdapter()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.apply {
            this.articles.adapter = adapter
            this.articles.layoutManager = layoutManager
        }
        subscribeUi(adapter)
        return binding.root
    }
    private fun subscribeUi(adapter: ArticlesAdapter) {
        viewModel.articles.observe(viewLifecycleOwner) { articleList ->
            adapter.submitList(articleList)
        }
    }
    companion object {
        private val TAG = MainFragment::class.java.simpleName
    }
}
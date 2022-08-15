package uz.gita.talkiesjt.presenter.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.talkiesjt.R
import uz.gita.talkiesjt.data.model.ArticleData
import uz.gita.talkiesjt.data.model.ChipData
import uz.gita.talkiesjt.databinding.ScreenHomeBinding
import uz.gita.talkiesjt.presenter.ui.adapter.ArticleAdapter
import uz.gita.talkiesjt.presenter.ui.adapter.ChipAdapter
import uz.gita.talkiesjt.presenter.viewmodel.HomeViewModel
import uz.gita.talkiesjt.presenter.viewmodel.impl.HomeViewModelImpl

@AndroidEntryPoint
class HomeScreen: Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel : HomeViewModel by viewModels<HomeViewModelImpl>()
    private val chipAdapter: ChipAdapter = ChipAdapter()
    private val articleAdapter: ArticleAdapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.webViewScreenLiveData.observe(this, webViewScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkSearchViewBehaviours()
        setChipRecycler()
        setArticleRecycler()

        viewModel.loadMostPopularArticles()


        // Observer
        viewModel.mostPopularArticlesLiveData.observe(viewLifecycleOwner, mostPopularArticlesObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, isLoadingObserver)
        viewModel.notConnectedLiveData.observe(viewLifecycleOwner, notConnectedObserver)
    }

    // Observers
    private val mostPopularArticlesObserver = Observer<List<ArticleData>> {
        articleAdapter.submitList(it)
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), "error -> $it", Toast.LENGTH_SHORT).show()
        Log.d("TTT", "error -> $it" )
    }

    private val isLoadingObserver = Observer<Boolean> {
        if(it) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private val notConnectedObserver = Observer<Boolean> {
        val snackbar = Snackbar.make(binding.root, R.string.not_connected, Snackbar.LENGTH_INDEFINITE)

        if(it) {
            snackbar.show()

        }
        else snackbar.dismiss()
    }

    private val webViewScreenObserver = Observer<ArticleData> {
        findNavController().navigate(HomeScreenDirections.actionHomeScreenToWebViewArticleScreen(it))
    }



    // Private functions

    private fun checkSearchViewBehaviours() {

    }

    private fun setArticleRecycler() {
        binding.recyclerViewForArticles.adapter = articleAdapter
        binding.recyclerViewForArticles.layoutManager = LinearLayoutManager(requireContext())

        articleAdapter.setOnArticleClickListener {
            viewModel.openWebViewScreen(it)
        }
    }

    private fun setChipRecycler() {
        binding.recyclerViewForChips.adapter = chipAdapter
        chipAdapter.submitList(
            listOf(
                ChipData(1,"arts"),
                ChipData(2,"automobiles"),
                ChipData(3,"books"),
                ChipData(4,"business"),
                ChipData(5,"fashion"),
                ChipData(6,"movies"),
                ChipData(7,"politics"),
                ChipData(8,"sports"),
                ChipData(9,"technology"),
                ChipData(10,"science"),

            )
        )

        chipAdapter.setOnChipClickListener {
            if(it.checked)
                viewModel.loadTopStories(it.text)
            else viewModel.loadMostPopularArticles()
        }
    }
}
package com.fathirdev.basketball_n

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fathirdev.basketball_n.adapter.NewsAdapter
import com.fathirdev.basketball_n.databinding.ActivitySearchResultsBinding

class SearchResultsActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private var _binding: ActivitySearchResultsBinding? = null
    private val binding get() = _binding as ActivitySearchResultsBinding

    private var _searchViewModel: NewsViewModel? = null
    private val searchViewModel get() = _searchViewModel as NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _searchViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        handleIntent(intent)



        binding.searchNews.setOnQueryTextListener(this)

        searchViewModel.searchNews.observe(this) {
            binding.rvSearchResult.apply {
                val mAdapter = NewsAdapter()
                mAdapter.setData(it.articles)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@SearchResultsActivity)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        // kotlin scope - also
        //mengambil value dari code yang mengimplementasikannya kemudian memberikannya ke dalam body lambda expression
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        searchViewModel.searchNews(query)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchNews.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            clearFocus()
            queryHint = "Search News..."
            setQuery("", false)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return when(!newText.isNullOrBlank()) {
            true -> {
                searchViewModel.searchNews(newText)
                true
            }
            else -> false

        }
    }



}
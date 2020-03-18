package com.example.kotlinblog.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinblog.R
import com.example.kotlinblog.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DividerItemDecoration



class NewsFragment : Fragment() {

    private lateinit var articleListViewModel: ArticleListViewModel
    private lateinit var binding: FragmentNewsBinding
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.articleList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.articleList.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        articleListViewModel = ArticleListViewModel()
        articleListViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = articleListViewModel
        return binding.root
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, articleListViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
package com.example.kotlinblog.ui.blog

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
import com.example.kotlinblog.databinding.FragmentBlogBinding
import com.google.android.material.snackbar.Snackbar


class BlogFragment : Fragment() {

    private lateinit var postListViewModel: PostListViewModel
    private lateinit var binding: FragmentBlogBinding
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blog, container, false)
        binding.postList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        postListViewModel = PostListViewModel()
        postListViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = postListViewModel
        return binding.root
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, postListViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
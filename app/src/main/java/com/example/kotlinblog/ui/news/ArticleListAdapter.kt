package com.example.kotlinblog.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinblog.R
import com.example.kotlinblog.databinding.ItemArticleBinding
import com.example.kotlinblog.model.news.Article

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    private lateinit var articleList: List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
        return if (::articleList.isInitialized) articleList.size else 0
    }

    fun updateArticleList(articleList: List<Article>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ArticleViewModel()

        fun bind(article: Article) {
            viewModel.bind(article)
            binding.viewModel = viewModel
        }
    }
}
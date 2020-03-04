package com.example.kotlinblog.ui.news

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.kotlinblog.BaseViewModel
import com.example.kotlinblog.R
import com.example.kotlinblog.model.news.Article
import com.example.kotlinblog.network.news.NewsApi
import com.example.kotlinblog.network.news.NewsResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel() : BaseViewModel() {
    @Inject
    lateinit var newsApi: NewsApi
    val articleListAdapter: ArticleListAdapter = ArticleListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadArticles() }

    private lateinit var subscription: Disposable

    init {
        loadArticles()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadArticles() {
        subscription = newsApi.getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveArticleListStart() }
            .doOnTerminate { onRetrieveArticleListFinish() }
            .subscribe(
                { result -> onRetrieveArticleListSuccess(result) },
                { error ->
                    print(error.message)
                    onRetrieveArticleListError()
                }
            )
    }

    private fun onRetrieveArticleListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveArticleListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveArticleListSuccess(response: NewsResponse) {
        articleListAdapter.updateArticleList(response.articles)
    }

    private fun onRetrieveArticleListError() {
        errorMessage.value = R.string.article_error
    }
}
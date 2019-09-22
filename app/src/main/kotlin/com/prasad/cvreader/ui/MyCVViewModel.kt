package com.prasad.cvreader.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.prasad.cvreader.R
import com.prasad.cvreader.base.BaseViewModel
import com.prasad.cvreader.model.Post
import com.prasad.cvreader.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyCVViewModel : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val response: MutableLiveData<Post> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private var subscription = CompositeDisposable()

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {
        subscription.add(postApi.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                        { result -> onRetrieveSuccess(result) },
                        { onRetrieveError() }
                ))
    }

    private fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveSuccess(response: Post) {
        this.response.value = response
    }

    private fun onRetrieveError() {
        errorMessage.value = R.string.post_error
    }
}
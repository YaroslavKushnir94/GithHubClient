package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.screens.LiveDataParams
import com.kushnir.githhubclient.view.screens.ResultTuple
import com.kushnir.githhubclient.view.screens.utils.SingleLiveEvent
import com.kushnir.githhubclient.view.screens.State
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesModelMapper
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val getRepositories: GetRepositories) : ViewModel() {
    private val PER_PAGE = 100
    private var page = 0
    private var key: String = ""
    private val generalData: MutableList<RepositoryModel> = mutableListOf()
    val mutableLiveData: SingleLiveEvent<LiveDataParams<RepositoriesStateModel>> = SingleLiveEvent()

    init {
        mutableLiveData.value = LiveDataParams(RepositoriesStateModel(), State.DONE)
    }

    fun loadStartPage(key: String, page: Int) {
        this.page = page
        if (this.key == key && generalData.isNotEmpty()) {
            mutableLiveData.postValue(LiveDataParams(RepositoriesStateModel(false, ResultTuple(generalData, page == 1)), State.DONE))
            this.page = generalData.size.div(PER_PAGE)
            Log.i("tag", "Old data used") // TODO need delete logs

        } else {
            this.key = key
            load(key)
        }
    }

    fun loadNextPages(key: String) {
        page++
        load(key)
    }

    private fun load(key: String) {
        Log.i("tag", "Start page ${this.page}  perPage $PER_PAGE")
        mutableLiveData.postValue(LiveDataParams(RepositoriesStateModel(true), State.DONE))
        getRepositories.execute(RepositoryObserver(), GetRepositories.Params(key, this.page, PER_PAGE))
    }

    inner class RepositoryObserver : DisposableObserver<MutableList<Repository>>() {

        override fun onComplete() {
        }

        override fun onNext(value: MutableList<Repository>?) {
            val resultList = RepositoriesModelMapper.transform(value!!)
            addToCollection(resultList)
            mutableLiveData.postValue(LiveDataParams(RepositoriesStateModel(false, ResultTuple(resultList, page == 1)), State.DONE))
            Log.i("tag", "Finish page $page}  generalCount ${generalData.size}")

        }

        override fun onError(e: Throwable?) {
            Log.i("tag", "Error page $page}  ")
            mutableLiveData.postValue(LiveDataParams(RepositoriesStateModel(false), State.ERROR))
        }
    }

    private fun addToCollection(items: MutableList<RepositoryModel>) {
        if (page == 1) {
            generalData.clear()
        }
        generalData.addAll(items)
    }

    override fun onCleared() {
        getRepositories.dispose()
        super.onCleared()
    }
}
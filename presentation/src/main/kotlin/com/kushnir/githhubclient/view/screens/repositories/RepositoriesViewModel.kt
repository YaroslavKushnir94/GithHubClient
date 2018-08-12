package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.screens.State
import com.kushnir.githhubclient.view.screens.VModelState
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesModelMapper
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import com.kushnir.githhubclient.view.screens.utils.Constants
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val getRepositories: GetRepositories) : ViewModel() {

    var liveData = MutableLiveData<VModelState<MutableList<RepositoryModel>, RepositoriesStateModel>>()
    private var savedStateMode = false

    init {
        liveData.value = VModelState(mutableListOf(), RepositoriesStateModel(), State.IDLE)
    }

    private var page = 0


    fun initialLoad(key: String) {
        page = 1
        liveData.value = VModelState(mutableListOf(), RepositoriesStateModel(true), State.LOAD)
        load(key)
    }

    fun loadNextPage(key: String) {
        Log.i("tag", "load next page")
        liveData.value = VModelState(mutableListOf(), RepositoriesStateModel(true), State.LOAD)
        load(key)
    }

    private fun load(key: String) {
        getRepositories.execute(RepositoryObserver(), GetRepositories.Params(key, page, Constants.PER_PAGE))
    }

    inner class RepositoryObserver : DisposableObserver<MutableList<Repository>>() {

        override fun onComplete() {}

        override fun onNext(value: MutableList<Repository>?) {
            update(value)
            page++
        }

        override fun onError(e: Throwable?) {

        }
    }

    fun saveData(data: MutableList<RepositoryModel>) {
        if (liveData.value!!.state == State.LOAD) {
            Log.i("tag", "state loading")
            savedStateMode = true
        }
        liveData.value = VModelState(data, RepositoriesStateModel(false), liveData.value!!.state)
        Log.i("tag", "saved state")
    }

    fun update(value: MutableList<Repository>?) {
        value?.let {
            liveData.value = if (savedStateMode && page > 1) {
                val lastList = liveData.value?.data ?: mutableListOf()
                lastList.addAll(RepositoriesModelMapper.transform(it))
                Log.i("tag", "added to previous list ${lastList.size} ")
                VModelState(lastList, RepositoriesStateModel(false), State.DONE)
            } else {
                Log.i("tag", "created a new list ${it.size}")
                VModelState(RepositoriesModelMapper.transform(it), RepositoriesStateModel(false), State.DONE)
            }
            savedStateMode = false
        }
    }

    override fun onCleared() {
        getRepositories.dispose()
        super.onCleared()
    }
}
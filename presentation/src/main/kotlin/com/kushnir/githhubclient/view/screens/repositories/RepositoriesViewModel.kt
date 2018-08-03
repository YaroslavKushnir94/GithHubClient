package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesModelMapper
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val getRepositories: GetRepositories) : ViewModel() {

    val mutableLiveData: MutableLiveData<RepositoriesStateModel> = MutableLiveData()

    init {
        mutableLiveData.value = RepositoriesStateModel()
    }

    fun search(key: String) {
        mutableLiveData.postValue(RepositoriesStateModel(true, mutableLiveData.value!!.repositories))
        getRepositories.execute(RepositoryObserver(), key)
    }

    inner class RepositoryObserver : DisposableObserver<MutableList<Repository>>() {

        override fun onComplete() {
        }

        override fun onNext(value: MutableList<Repository>?) {
            mutableLiveData.postValue(RepositoriesStateModel(false, RepositoriesModelMapper.transform(value!!)))
        }

        override fun onError(e: Throwable?) {
            mutableLiveData.postValue(RepositoriesStateModel(false, mutableListOf()))
        }
    }

    override fun onCleared() {
        getRepositories.dispose()
        super.onCleared()
    }
}
package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kushnir.domain.entities.User
import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModelMapper
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(private val getUserFollowers: GetUserFollowers) : ViewModel() {

    val mutableLiveData: MutableLiveData<RepoDetailsStateModel> = MutableLiveData()
    init {
        mutableLiveData.value = RepoDetailsStateModel()
    }

    fun getFollowers(name: String) {
        mutableLiveData.postValue(RepoDetailsStateModel(true, 0, mutableLiveData.value!!.followers))
        getUserFollowers.execute(FollowersObservable(), name)
    }

    inner class FollowersObservable : DisposableObserver<MutableList<User>>() {

        override fun onComplete() {}

        override fun onNext(value: MutableList<User>?) {
            mutableLiveData.postValue(RepoDetailsStateModel(false, value!!.size, UserModelMapper.transform(value)))
        }
        override fun onError(e: Throwable?) {
            mutableLiveData.postValue(RepoDetailsStateModel(false, 0, mutableListOf()))
        }
    }

    override fun onCleared() {
        getUserFollowers.dispose()
        super.onCleared()
    }
}
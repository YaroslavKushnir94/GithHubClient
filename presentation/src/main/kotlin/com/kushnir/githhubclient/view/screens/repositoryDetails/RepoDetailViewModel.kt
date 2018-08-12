package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kushnir.domain.entities.User
import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.githhubclient.view.screens.State
import com.kushnir.githhubclient.view.screens.VModelState
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModelMapper
import com.kushnir.githhubclient.view.screens.utils.Constants
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RepoDetailViewModel @Inject constructor(private val getUserFollowers: GetUserFollowers) : ViewModel() {

    val liveData = MutableLiveData<VModelState<MutableList<UserModel>, RepoDetailsStateModel>>()
    private var page: Int = 0
    private var countFollowers = 0
    private var savedStateMode = false

    init {
        liveData.value = VModelState(mutableListOf(), RepoDetailsStateModel(), State.IDLE)
    }

    fun initialLoad(name: String) {
        if(liveData.value!!.data.isEmpty()) {
            liveData.value = VModelState(mutableListOf(), RepoDetailsStateModel(true), State.LOAD)
            page = 1
            countFollowers = 0
            load(name)
        }
    }

    fun loadNextPage(name: String) {
        liveData.value = VModelState(mutableListOf(), RepoDetailsStateModel(true), State.LOAD)
        load(name)
    }

    private fun load(name: String) {
        getUserFollowers.execute(FollowersObservable(), GetUserFollowers.Params(name, page, Constants.PER_PAGE))
    }

    private inner class FollowersObservable : DisposableObserver<MutableList<User>>() {

        override fun onComplete() {}

        override fun onNext(value: MutableList<User>?) {
            val resultList = UserModelMapper.transform(value!!)
            countFollowers += resultList.size
            update(value)
            page++
        }
        override fun onError(e: Throwable?) {
        }
    }

    fun update(value: MutableList<User>?) {
        value?.let {
            liveData.value = if (savedStateMode) {
                val lastList = liveData.value?.data ?: mutableListOf()
                lastList.addAll( UserModelMapper.transform(it))
                Log.i("tag","update previous list  ${lastList.size}" )
                VModelState(lastList, RepoDetailsStateModel(false,countFollowers), State.DONE)
            } else {
                Log.i("tag","create new list  " )
                VModelState(UserModelMapper.transform(it), RepoDetailsStateModel(false,countFollowers), State.DONE)
            }
            savedStateMode = false
        }
    }

    fun saveData(data: MutableList<UserModel>) {
        if (liveData.value!!.state == State.LOAD) {
            Log.i("tag","saved block state load")
            savedStateMode = true
        }
        liveData.value = VModelState(data, RepoDetailsStateModel(false), liveData.value!!.state)
        Log.i("tag","just saved block")

    }

    override fun onCleared() {
        getUserFollowers.dispose()
        super.onCleared()
    }
}
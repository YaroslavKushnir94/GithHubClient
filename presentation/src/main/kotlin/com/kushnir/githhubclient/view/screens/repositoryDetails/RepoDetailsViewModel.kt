package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kushnir.domain.entities.User
import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.githhubclient.view.screens.LiveDataParams
import com.kushnir.githhubclient.view.screens.ResultTuple
import com.kushnir.githhubclient.view.screens.State
import com.kushnir.githhubclient.view.screens.repositories.RepositoriesStateModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModelMapper
import com.kushnir.githhubclient.view.screens.utils.SingleLiveEvent
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(private val getUserFollowers: GetUserFollowers) : ViewModel() {

    val liveData: SingleLiveEvent<LiveDataParams<RepoDetailsStateModel>> = SingleLiveEvent()
    private val generalData: MutableList<UserModel> = mutableListOf()

    private val PER_PAGE = 100
    private var name: String = ""
    private var page: Int = 0

    init {
        liveData.value = LiveDataParams(RepoDetailsStateModel(), State.DONE)
    }

    fun loadStartPage(name: String) {
        this.name = name
        page = 1
        if (generalData.isNotEmpty()) {
            liveData.postValue(LiveDataParams(RepoDetailsStateModel(false, generalData.size, generalData), State.DONE))
            this.page = generalData.size.div(PER_PAGE)
            Log.i("tag", "Old data used") // TODO need delete logs
        } else {
            load()
            Log.i("tag", "start load  page $page")
        }
    }

    fun loadNextPage() {
        page++
        Log.i("tag", "more load page $page")
        load()
    }

    private fun load() {
        liveData.postValue(LiveDataParams(RepoDetailsStateModel(true), State.DONE))
        getUserFollowers.execute(FollowersObservable(), GetUserFollowers.Params(name, page, PER_PAGE))
    }

    inner class FollowersObservable : DisposableObserver<MutableList<User>>() {

        override fun onComplete() {}

        override fun onNext(value: MutableList<User>?) {
            val resultList = UserModelMapper.transform(value!!)
            addToCollection(resultList)
            liveData.postValue(LiveDataParams(RepoDetailsStateModel(false, generalData.size, resultList), State.DONE))
            Log.i("tag", "finish load $page generalCount ${generalData.size}")

        }

        override fun onError(e: Throwable?) {
            liveData.postValue(LiveDataParams(RepoDetailsStateModel(false), State.ERROR))
            Log.i("tag", "error load $page")

        }
    }

    private fun addToCollection(items: MutableList<UserModel>) {
        if (page == 1) {
            generalData.clear()
        }
        generalData.addAll(items)
    }

    override fun onCleared() {
        getUserFollowers.dispose()
        super.onCleared()
    }
}
package com.kushnir.githhubclient.view.screens.repositoryDetails

import com.kushnir.githhubclient.view.screens.utils.Constants.Companion.PER_PAGE
import com.kushnir.domain.entities.User
import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.githhubclient.view.exception.ErrorHandler
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModelMapper
import io.reactivex.observers.DisposableObserver

class RepoDetailsPresenter(private val getUserFollowers: GetUserFollowers) : RepoDetailsScreen.Presenter {

    private lateinit var view: RepoDetailsScreen.View

    private var page: Int = 0
    private var countFollowers = 0

    override fun onViewCreated(view: RepoDetailsScreen.View) {
        this.view = view
    }

    override fun getFollowers(name: String, page: Int) {
        this.page = page
        countFollowers = 0
        load(name)
    }

    override fun loadMoreFollowers(name: String) {
        load(name)
    }

    private fun load(name: String) {
        view.changeData(RepoDetailsStateModel(true))
        getUserFollowers.execute(FollowersObservable(), GetUserFollowers.Params(name, page, PER_PAGE))
    }

    inner class FollowersObservable : DisposableObserver<MutableList<User>>() {

        override fun onComplete() {}

        override fun onNext(value: MutableList<User>?) {
            val resultList = UserModelMapper.transform(value!!)
            countFollowers += resultList.size
            view.changeData(RepoDetailsStateModel(false, countFollowers, resultList))
            page++
        }

        override fun onError(e: Throwable?) {
            view.changeData(RepoDetailsStateModel(false))
            view.showErrorMessage(ErrorHandler.getMessage(e as Exception?))
        }
    }

    override fun onViewDestroy() {
        getUserFollowers.dispose()
    }
}
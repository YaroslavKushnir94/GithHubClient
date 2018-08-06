package com.kushnir.githhubclient.view.screens.repositories

import android.util.Log
import com.kushnir.githhubclient.view.screens.utils.Constants.Companion.PER_PAGE
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.exception.ErrorHandler
import com.kushnir.githhubclient.view.screens.utils.ResultTuple
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesModelMapper
import io.reactivex.observers.DisposableObserver

class RepositoriesPresenter(private val getRepositories: GetRepositories) : RepositoriesScreen.Presenter {

    private var page = 0

    private lateinit var view: RepositoriesScreen.View

    private var isNewRequest = true

    override fun onViewCreated(view: RepositoriesScreen.View) {
        this.view = view
    }

    override fun searchTextChanged(key: String, page: Int) {
        this.page = page
        isNewRequest = true
        load(key)
    }

    override fun loadMore(key: String) {
        if (isNewRequest) page = 2
        isNewRequest = false
        load(key)
    }

    private fun load(key: String) {
        view.changeData(RepositoriesStateModel(true))
        getRepositories.execute(RepositoryObserver(), GetRepositories.Params(key, this.page, PER_PAGE))
    }

    inner class RepositoryObserver : DisposableObserver<MutableList<Repository>>() {

        override fun onComplete() {}

        override fun onNext(value: MutableList<Repository>?) {
            view.changeData(RepositoriesStateModel(false, ResultTuple(RepositoriesModelMapper.transform(value!!), isNewRequest)))
            page++
        }

        override fun onError(e: Throwable?) {
            view.changeData(RepositoriesStateModel(false))
            view.showErrorMessage(ErrorHandler.getMessage(e as Exception?))
        }
    }

    override fun onViewDestroy() {
        getRepositories.dispose()
    }
}
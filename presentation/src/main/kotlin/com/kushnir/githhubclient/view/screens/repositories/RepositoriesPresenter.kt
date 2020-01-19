package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.kushnir.githhubclient.view.screens.utils.Constants.Companion.PER_PAGE
import com.kushnir.domain.entities.Repository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.exception.ErrorHandler
import com.kushnir.githhubclient.view.pagging.DataSourceFactory
import com.kushnir.githhubclient.view.screens.utils.ResultTuple
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesModelMapper
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.Executors

class RepositoriesPresenter(private val getRepositories: GetRepositories) : RepositoriesScreen.Presenter {

    private var page = 0

    private lateinit var view: RepositoriesScreen.View

    private var isNewRequest = true

    val repositoryLiveData: LiveData<PagedList<RepositoryModel>>

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
//        view.changeData(RepositoriesStateModel(true))
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

    init {
        val sourceFactory = DataSourceFactory(getRepositories, GetRepositories.Params("Android", 1, 100))

        val pageConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build()

        repositoryLiveData = LivePagedListBuilder(sourceFactory, pageConfig)
                .setFetchExecutor(Executors.newFixedThreadPool(2))
                .build()
    }

    override fun getLiveData(): LiveData<PagedList<RepositoryModel>> = repositoryLiveData

    override fun onViewDestroy() {
        getRepositories.dispose()
    }
}
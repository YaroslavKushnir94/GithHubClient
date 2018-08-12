package com.kushnir.githhubclient.view.screens.repositoryDetails

import com.kushnir.githhubclient.view.screens.utils.Constants.Companion.PER_PAGE
import com.kushnir.domain.entities.User
import com.kushnir.domain.interactor.GetUserFollowers
import com.kushnir.githhubclient.view.exception.ErrorHandler
import com.kushnir.githhubclient.view.screens.State
import com.kushnir.githhubclient.view.screens.VModelState
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModelMapper
import io.reactivex.observers.DisposableObserver

class RepoDetailsPresenter : RepoDetailsScreen.Presenter {

    private lateinit var view: RepoDetailsScreen.View
    private lateinit var viewModel: RepoDetailViewModel


    override fun onViewCreated(view: RepoDetailsScreen.View) {
        this.view = view
    }

    override fun addViewModel(viewModel: RepoDetailViewModel) {
        this.viewModel = viewModel
    }

    override fun getFollowers(name: String, page: Int) {
        viewModel.initialLoad(name)
    }

    override fun loadMoreFollowers(name: String) {
        viewModel.loadNextPage(name)
    }

    override fun onChanged(t: VModelState<MutableList<UserModel>, RepoDetailsStateModel>?) {
        when (t!!.state) {
            State.LOAD -> {
                view.changeViewModel(t.viewModel)
            }
            State.DONE -> {
                view.changeViewModel(t.viewModel)
                view.changeData(t.data)
            }
            State.IDLE -> {
            }
            State.ERROR -> {
            }
        }
    }

    override fun onSavedState(data: MutableList<UserModel>) {
        viewModel.saveData(data)
    }
}
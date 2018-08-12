package com.kushnir.githhubclient.view.screens.repositories

import com.kushnir.githhubclient.view.screens.State
import com.kushnir.githhubclient.view.screens.VModelState
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

class RepositoriesPresenter : RepositoriesScreen.Presenter {


    private lateinit var view: RepositoriesScreen.View
    private lateinit var viewModel: RepositoriesViewModel


    override fun onViewCreated(view: RepositoriesScreen.View) {
        this.view = view
    }

    override fun addViewModel(viewModel: RepositoriesViewModel) {
        this.viewModel = viewModel
    }

    override fun searchTextChanged(key: String, page: Int) {
        viewModel.initialLoad(key)
    }

    override fun loadMore(key: String) {
        viewModel.loadNextPage(key)
    }

    override fun onSavedState(data: MutableList<RepositoryModel>) {
        viewModel.saveData(data)
    }

    override fun onChanged(t: VModelState<MutableList<RepositoryModel>, RepositoriesStateModel>?) {
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
}
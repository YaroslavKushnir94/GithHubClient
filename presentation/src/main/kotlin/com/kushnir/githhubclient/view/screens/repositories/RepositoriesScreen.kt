package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.Observer
import com.kushnir.githhubclient.view.screens.repositories.adapter.AdapterClickListener
import com.kushnir.githhubclient.view.base.BaseScreen
import com.kushnir.githhubclient.view.screens.VModelState
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

interface RepositoriesScreen {
    interface View : BaseScreen.View, AdapterClickListener {
        fun changeData(items: MutableList<RepositoryModel>)
        fun changeViewModel(viewModel: RepositoriesStateModel)
        fun showErrorMessage(message: String)
    }

    interface Presenter : BaseScreen.Presenter<View>, Observer<VModelState<MutableList<RepositoryModel>, RepositoriesStateModel>> {
        fun searchTextChanged(key: String, page: Int)
        fun loadMore(key: String)
        fun addViewModel(viewModel: RepositoriesViewModel)
        fun onSavedState(data: MutableList<RepositoryModel>)
    }

}
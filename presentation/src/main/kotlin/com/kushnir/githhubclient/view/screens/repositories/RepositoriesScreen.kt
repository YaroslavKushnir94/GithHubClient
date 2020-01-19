package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.kushnir.githhubclient.view.screens.repositories.adapter.AdapterClickListener
import com.kushnir.githhubclient.view.base.BaseScreen
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

interface RepositoriesScreen {
    interface View : BaseScreen.View, AdapterClickListener {
        fun changeData(state: RepositoriesStateModel)
        fun showErrorMessage(message:String)
    }

    interface Presenter : BaseScreen.Presenter<View> {
        fun searchTextChanged(key: String, page: Int)
        fun loadMore(key: String)
        fun onViewDestroy()
        fun getLiveData(): LiveData<PagedList<RepositoryModel>>
    }

}
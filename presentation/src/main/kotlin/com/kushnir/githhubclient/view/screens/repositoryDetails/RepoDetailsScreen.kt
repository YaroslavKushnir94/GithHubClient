package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.Observer
import com.kushnir.githhubclient.view.base.BaseScreen
import com.kushnir.githhubclient.view.screens.VModelState
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModel

interface RepoDetailsScreen {

    interface View : BaseScreen.View {
        fun changeData(data:MutableList<UserModel>)
        fun changeViewModel(model: RepoDetailsStateModel)
        fun showErrorMessage(message:String)
    }

    interface Presenter : BaseScreen.Presenter<View>,Observer<VModelState<MutableList<UserModel>,RepoDetailsStateModel>>{
        fun addViewModel(viewModel: RepoDetailViewModel)
        fun getFollowers(name: String,page:Int)
        fun loadMoreFollowers(name:String)
        fun onSavedState(data: MutableList<UserModel>)
    }
}
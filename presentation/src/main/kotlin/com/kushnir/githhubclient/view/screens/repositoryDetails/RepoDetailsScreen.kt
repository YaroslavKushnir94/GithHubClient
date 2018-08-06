package com.kushnir.githhubclient.view.screens.repositoryDetails

import com.kushnir.githhubclient.view.base.BaseScreen

interface RepoDetailsScreen {

    interface View : BaseScreen.View {
        fun changeData(state:RepoDetailsStateModel)
        fun showErrorMessage(message:String)

    }

    interface Presenter : BaseScreen.Presenter<View>{
        fun getFollowers(name: String,page:Int)
        fun loadMoreFollowers(name:String)
        fun onViewDestroy()
    }
}
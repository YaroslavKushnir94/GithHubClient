package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.Observer
import com.kushnir.githhubclient.view.base.BaseScreen
import com.kushnir.githhubclient.view.screens.LiveDataParams

interface RepoDetailsScreen {

    interface View : BaseScreen.View {
        fun changeData(state:RepoDetailsStateModel)
    }

    interface Presenter : BaseScreen.Presenter<View, RepoDetailsViewModel>,Observer<LiveDataParams<RepoDetailsStateModel>> {
        fun getFollowers(name: String)
        fun loadMoreFollowers()
    }
}
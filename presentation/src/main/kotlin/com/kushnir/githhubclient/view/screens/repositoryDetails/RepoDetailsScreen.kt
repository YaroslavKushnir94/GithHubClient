package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.Observer
import com.kushnir.githhubclient.view.base.BaseScreen

interface RepoDetailsScreen {

    interface View : BaseScreen.View {
        fun onDataChanged(state:RepoDetailsStateModel)
    }

    interface Presenter : BaseScreen.Presenter<View, RepoDetailsViewModel>,Observer<RepoDetailsStateModel> {
        fun getFollowers(name: String)
    }
}
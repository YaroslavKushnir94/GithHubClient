package com.kushnir.githhubclient.view.screens.repositoryDetails

import com.kushnir.githhubclient.view.screens.LiveDataParams
import com.kushnir.githhubclient.view.screens.State

class RepoDetailsPresenter : RepoDetailsScreen.Presenter {

    private lateinit var view: RepoDetailsScreen.View
    private lateinit var model: RepoDetailsViewModel

    override fun onViewCreated(view: RepoDetailsScreen.View, model: RepoDetailsViewModel) {
        this.view = view
        this.model = model
    }

    override fun getFollowers(name: String) {
        model.loadStartPage(name)
    }

    override fun loadMoreFollowers() {
        model.loadNextPage()
    }

    override fun onChanged(t: LiveDataParams<RepoDetailsStateModel>?) {
        when (t!!.state) {
            State.DONE -> view.changeData(t.data)
            State.ERROR -> { view.changeData(t.data) }
        }
    }
}
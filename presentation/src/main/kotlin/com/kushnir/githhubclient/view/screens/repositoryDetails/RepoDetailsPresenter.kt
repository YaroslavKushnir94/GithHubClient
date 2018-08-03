package com.kushnir.githhubclient.view.screens.repositoryDetails

class RepoDetailsPresenter : RepoDetailsScreen.Presenter {
    private lateinit var view: RepoDetailsScreen.View
    private lateinit var model: RepoDetailsViewModel

    override fun onViewCreated(view: RepoDetailsScreen.View, model: RepoDetailsViewModel) {
        this.view = view
        this.model = model
    }

    override fun getFollowers(name: String) {
        model.getFollowers(name)
    }

    override fun onChanged(t: RepoDetailsStateModel?) {
        view.onDataChanged(t!!)
    }
}
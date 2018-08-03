package com.kushnir.githhubclient.view.screens.repositories

class RepositoriesPresenter : RepositoriesScreen.Presenter {

    private lateinit var view: RepositoriesScreen.View
    private lateinit var model: RepositoriesViewModel

    override fun onViewCreated(view: RepositoriesScreen.View, model: RepositoriesViewModel) {
        this.view = view
        this.model = model
    }

    override fun searchTextChanged(key: String) {
        model.search(key)
    }

    override fun onChanged(t: RepositoriesStateModel?) {
        view.onDataChanged(t!!)
    }
}
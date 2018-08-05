package com.kushnir.githhubclient.view.screens.repositories

import com.kushnir.githhubclient.view.screens.LiveDataParams
import com.kushnir.githhubclient.view.screens.State

class RepositoriesPresenter : RepositoriesScreen.Presenter {

    private lateinit var view: RepositoriesScreen.View
    private lateinit var model: RepositoriesViewModel

    override fun onViewCreated(view: RepositoriesScreen.View, model: RepositoriesViewModel) {
        this.view = view
        this.model = model
    }

    override fun searchTextChanged(key: String, page: Int) {
        model.loadStartPage(key, page)
    }

    override fun loadMore(key: String) {
        model.loadNextPages(key)
    }

    override fun onChanged(t: LiveDataParams<RepositoriesStateModel>?) {
        when (t!!.state) {
            State.DONE -> view.changeData(t.data)
            State.ERROR -> {
                view.changeData(t.data)
            }
        }
    }
}
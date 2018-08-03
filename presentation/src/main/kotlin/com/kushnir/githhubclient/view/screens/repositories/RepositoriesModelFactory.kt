package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kushnir.domain.interactor.GetRepositories

class RepositoriesModelFactory(private val getRepositories: GetRepositories) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoriesViewModel(getRepositories) as T
    }
}

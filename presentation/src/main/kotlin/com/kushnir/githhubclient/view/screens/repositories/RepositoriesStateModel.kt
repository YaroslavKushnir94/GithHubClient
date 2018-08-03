package com.kushnir.githhubclient.view.screens.repositories

import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel


class RepositoriesStateModel(var loading: Boolean = false, var repositories: MutableList<RepositoryModel> = mutableListOf())
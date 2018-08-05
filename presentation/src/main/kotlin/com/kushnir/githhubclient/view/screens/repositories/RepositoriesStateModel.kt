package com.kushnir.githhubclient.view.screens.repositories

import com.kushnir.githhubclient.view.screens.ResultTuple
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

data class RepositoriesStateModel(var loading: Boolean = false, var data: ResultTuple<MutableList<RepositoryModel>> = ResultTuple(mutableListOf(), false))
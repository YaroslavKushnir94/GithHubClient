package com.kushnir.githhubclient.view.screens.repositoryDetails

import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModel

data class RepoDetailsStateModel(val loading:Boolean = false,val countFollowers:Int = 0,var followers:MutableList<UserModel> = mutableListOf())
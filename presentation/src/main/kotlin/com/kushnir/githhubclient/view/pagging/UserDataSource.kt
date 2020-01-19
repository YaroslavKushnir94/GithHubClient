package com.kushnir.githhubclient.view.pagging

import com.kushnir.domain.entities.Repository
import com.kushnir.domain.interactor.GetRepositories
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesModelMapper
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel

class UserDataSource(

        repositories: GetRepositories,
        params: GetRepositories.Params

) : AdapterDataSource<Repository, RepositoryModel, GetRepositories.Params>(repositories, params) {

    override fun updateRequestParams(requestParams: GetRepositories.Params) {
      requestParams.key = page
    }

    override fun toAppModel(items: MutableList<Repository>): MutableList<RepositoryModel> =
            RepositoriesModelMapper.transform(items)

}
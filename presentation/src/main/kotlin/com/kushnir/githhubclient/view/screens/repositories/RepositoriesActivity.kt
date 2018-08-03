package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.kushnir.githhubclient.R
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_repositories.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.databinding.DataBindingUtil
import android.util.Log
import com.kushnir.githhubclient.databinding.ActivityRepositoriesBinding
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesAdapter
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.RepoDetailsActivity
import com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable.DetailsParams


class RepositoriesActivity : AppCompatActivity(), RepositoriesScreen.View {

    private val DEBOUNCE = 500L

    @Inject
    lateinit var presenter: RepositoriesScreen.Presenter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: RepositoriesAdapter

    private lateinit var binding: ActivityRepositoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repositories)
        binding.stateModel = RepositoriesStateModel()
        intiView()
        presenter.onViewCreated(this, createViewModel())
    }

    private fun intiView() {
        rcRepo.layoutManager = LinearLayoutManager(this)
        rcRepo.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rcRepo.adapter = adapter
        adapter.addListener(this)
    }

    private fun createViewModel(): RepositoriesViewModel {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[RepositoriesViewModel::class.java]
        viewModel.mutableLiveData.observe(this, presenter)
        return viewModel
    }

    override fun onDataChanged(state: RepositoriesStateModel) {
        binding.stateModel = state
        adapter.addItems(state.repositories)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val mSearchMenuItem = menu!!.findItem(R.id.action_search)
        val searchView = mSearchMenuItem.actionView as SearchView
        RxSearchView.queryTextChanges(searchView)
                .debounce(DEBOUNCE, TimeUnit.MILLISECONDS)
                .filter { item -> item.toString().trim().isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.searchTextChanged(it.toString())
                }
        return true
    }

    override fun onClick(item: RepositoryModel) {
        startActivity(RepoDetailsActivity.getStartIntent(this,DetailsParams(item.fullName,item.login)))
    }
}
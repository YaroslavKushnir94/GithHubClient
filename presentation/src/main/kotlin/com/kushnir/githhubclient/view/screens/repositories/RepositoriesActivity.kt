package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.Observer
import android.os.Bundle
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
import com.kushnir.githhubclient.view.base.BaseActivity
import com.kushnir.githhubclient.view.base.PagingAdapter
import com.kushnir.githhubclient.view.pagging.PaggingListAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.RepoDetailsActivity
import com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable.DetailsParams
import com.kushnir.githhubclient.view.screens.repositories.adapter.*


class RepositoriesActivity : BaseActivity(), RepositoriesScreen.View {

    private val DEBOUNCE = 600L

    private val QUERY_DATA_PARAMS = "repository.params"

    @Inject
    lateinit var presenter: RepositoriesScreen.Presenter

    @Inject
    lateinit var adapter: RepositoriesAdapter

    private lateinit var binding: ActivityRepositoriesBinding

    private var key = "Kotlin"
    private lateinit var paggingAdapter: PaggingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repositories)
        binding.stateModel = RepositoriesStateModel()
        paggingAdapter = PaggingListAdapter(RepositoryModel.diffUtil)
        intiView()
        addListeners()
        getQuery(savedInstanceState)
        presenter.onViewCreated(this)
        presenter.searchTextChanged(key, 1)
    }

    private fun intiView() {
        setTitle(R.string.repositories_title)
        rcRepo.layoutManager = LinearLayoutManager(this)
        rcRepo.adapter = paggingAdapter
    }

    private fun addListeners() {
        adapter.addClickListener(this)
        adapter.addLoadingListener(object : PagingAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                presenter.loadMore(key)
            }
        })
        presenter.getLiveData().observe(this, Observer {
            paggingAdapter.submitList(it)
        })
    }

    override fun changeData(state: RepositoriesStateModel) {
        binding.stateModel = state
        adapter.addItems(state.data)
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
                    key = it.toString()
                    presenter.searchTextChanged(key, 1)
                }
        return true
    }

    override fun onClick(item: RepositoryModel) {
        startActivity(RepoDetailsActivity.getStartIntent(this, DetailsParams(item.fullName, item.login)))
    }

    override fun showErrorMessage(message: String) {
        displayMessage(message)
        adapter.loadFinish()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(QUERY_DATA_PARAMS, key)
    }

    private fun getQuery(bundle: Bundle?) {
        key = bundle?.getString(QUERY_DATA_PARAMS) ?: "Kotlin"
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
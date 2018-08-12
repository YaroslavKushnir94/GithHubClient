package com.kushnir.githhubclient.view.screens.repositories

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.FragmentRepositoriesBinding
import com.kushnir.githhubclient.view.base.BaseFragment
import com.kushnir.githhubclient.view.base.PagingAdapter
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoriesAdapter
import com.kushnir.githhubclient.view.screens.repositories.adapter.RepositoryModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable.DetailsParams
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repositories.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoriesFragment : BaseFragment(), RepositoriesScreen.View {

    private lateinit var binding: FragmentRepositoriesBinding

    @Inject
    lateinit var presenter: RepositoriesScreen.Presenter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: RepositoriesAdapter

    private var key: String = ""

    private val QUERY_DATA_PARAMS = "repository.params"


    companion object {
        fun newInstance(): Fragment = RepositoriesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        getQuery(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repositories, container, false)
        binding.stateModel = RepositoriesStateModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated(this)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[RepositoriesViewModel::class.java]
        viewModel.liveData.observe(this, presenter)
        presenter.addViewModel(viewModel)
        intiView()
        addListeners()
    }

    override fun changeData(items: MutableList<RepositoryModel>) {
        adapter.addItems(items)
    }

    override fun changeViewModel(viewModel: RepositoriesStateModel) {
        binding.stateModel = viewModel
    }

    override fun showErrorMessage(message: String) {
    }

    private fun intiView() {
        activity?.setTitle(R.string.repositories_title)
        rcRepo.layoutManager = LinearLayoutManager(activity)
        rcRepo.adapter = adapter
    }

    private fun addListeners() {
        adapter.addClickListener(this)
        adapter.addLoadingListener(object : PagingAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                adapter.newPortion = false
                presenter.loadMore(key)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.search_menu, menu)
        val mSearchMenuItem = menu!!.findItem(R.id.action_search)
        val searchView = mSearchMenuItem.actionView as SearchView
        RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter { item -> item.toString().trim().isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.newPortion = true
                    key = it.toString()
                    presenter.searchTextChanged(key, 1)
                }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSavedState(adapter.items)
        outState.putString(QUERY_DATA_PARAMS, key)
    }

    private fun getQuery(bundle: Bundle?) {
        key = bundle?.getString(QUERY_DATA_PARAMS) ?: "Kotlin"
    }

    override fun onClick(item: RepositoryModel) {
        navigator.openDetailsRepoFragment(DetailsParams(item.fullName,item.login))
    }
}
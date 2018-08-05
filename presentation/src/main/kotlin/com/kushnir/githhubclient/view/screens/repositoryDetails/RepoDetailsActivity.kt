package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.DetailsRepoActivityBinding
import com.kushnir.githhubclient.view.screens.repositories.adapter.PagingAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.RepoDetailsAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable.DetailsParams
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.details_repo_activity.*
import javax.inject.Inject

class RepoDetailsActivity : AppCompatActivity(), RepoDetailsScreen.View {

    @Inject
    lateinit var presenter: RepoDetailsScreen.Presenter

    @Inject
    lateinit var adapter: RepoDetailsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: DetailsRepoActivityBinding

    private lateinit var params: DetailsParams

    companion object {
        private val PARAMS_KEY = "parameters.key"

        fun getStartIntent(context: Context, params: Parcelable): Intent {
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra(PARAMS_KEY, params)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.details_repo_activity)
        binding.stateModel = RepoDetailsStateModel()
        params = intent.getParcelableExtra(PARAMS_KEY)
        initView()
        addListeners()
        presenter.onViewCreated(this, createViewModel())
        presenter.getFollowers(params.userName)
    }

    private fun createViewModel(): RepoDetailsViewModel {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[RepoDetailsViewModel::class.java]
        viewModel.liveData.observe(this, presenter)
        return viewModel
    }

    override fun changeData(state: RepoDetailsStateModel) {
        binding.stateModel = state
        adapter.addItems(state.followers)
    }

    private fun initView() {
        addHomeButton()
        tvRepositoryName.text = params.repoName
        rvFollowers.layoutManager = LinearLayoutManager(this)
        rvFollowers.adapter = adapter
    }

    private fun addListeners() {
        adapter.addLoadingListener(object : PagingAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                presenter.loadMoreFollowers()
            }
        })
    }

    private fun addHomeButton() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
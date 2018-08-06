package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.DetailsRepoActivityBinding
import com.kushnir.githhubclient.view.base.BaseActivity
import com.kushnir.githhubclient.view.base.PagingAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.RepoDetailsAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable.DetailsParams
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.details_repo_activity.*
import javax.inject.Inject

class RepoDetailsActivity : BaseActivity(), RepoDetailsScreen.View {

    @Inject
    lateinit var presenter: RepoDetailsScreen.Presenter

    @Inject
    lateinit var adapter: RepoDetailsAdapter

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
        presenter.onViewCreated(this)
        presenter.getFollowers(params.userName,1)
    }

    override fun changeData(state: RepoDetailsStateModel) {
        binding.stateModel = state
        adapter.addItems(state.followers)
    }

    private fun initView() {
        addHomeButton()
        setTitle(R.string.details_repo_title)
        tvRepositoryName.text = params.repoName
        rvFollowers.layoutManager = LinearLayoutManager(this)
        rvFollowers.adapter = adapter
    }

    private fun addListeners() {
        adapter.addLoadingListener(object : PagingAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                presenter.loadMoreFollowers(params.userName)
            }
        })
    }

    override fun showErrorMessage(message: String) {
        displayMessage(message)
        adapter.loadFinish()
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

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
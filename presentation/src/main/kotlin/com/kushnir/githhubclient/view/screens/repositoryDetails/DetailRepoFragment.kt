package com.kushnir.githhubclient.view.screens.repositoryDetails

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kushnir.githhubclient.R
import com.kushnir.githhubclient.databinding.DetailsRepoFragmentBinding
import com.kushnir.githhubclient.view.base.BaseFragment
import com.kushnir.githhubclient.view.base.PagingAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.RepoDetailsAdapter
import com.kushnir.githhubclient.view.screens.repositoryDetails.adapter.UserModel
import com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable.DetailsParams
import kotlinx.android.synthetic.main.details_repo_fragment.*
import javax.inject.Inject

class DetailRepoFragment : BaseFragment(), RepoDetailsScreen.View {

    private lateinit var params: DetailsParams

    private lateinit var binding: DetailsRepoFragmentBinding

    @Inject
    lateinit var presenter: RepoDetailsScreen.Presenter

    @Inject
    lateinit var adapter: RepoDetailsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        private val PARAMS_KEY = "parameters.key"

        fun newInstance(parcelable: Parcelable): Fragment {
            val bundle = Bundle()
            bundle.putParcelable(PARAMS_KEY, parcelable)
            val fragment = DetailRepoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        params = arguments?.getParcelable(PARAMS_KEY) ?: DetailsParams()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_repo_fragment, container, false)
        binding.stateModel = RepoDetailsStateModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListeners()
        presenter.onViewCreated(this)
        presenter.addViewModel(createViewModel())
        presenter.getFollowers(params.userName,1)
    }

    private fun createViewModel():RepoDetailViewModel{
        val viewModel = ViewModelProviders.of(this,viewModelFactory)[RepoDetailViewModel::class.java]
        viewModel.liveData.observe(this,presenter)
        return viewModel
    }

    private fun initView() {
        tvRepositoryName.text = params.repoName
        rvFollowers.layoutManager = LinearLayoutManager(activity)
        rvFollowers.adapter = adapter
    }

    private fun addListeners() {
        adapter.addLoadingListener(object : PagingAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                presenter.loadMoreFollowers(params.userName)
            }
        })
    }

    override fun changeData(data: MutableList<UserModel>) {
        adapter.addItems(data)
    }

    override fun changeViewModel(model: RepoDetailsStateModel) {
        binding.stateModel = model
    }

    override fun showErrorMessage(message: String) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSavedState(adapter.items)
    }
}

package com.kushnir.githhubclient.view.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kushnir.githhubclient.view.screens.utils.Constants

abstract class PagingAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    var isLastPage = false

    private var PORTION = Constants.PER_PAGE

    private var loadMoreListener: OnLoadMoreListener? = null

    private var loading: Boolean = false

    private var prefetchDistance = 10

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val countItems = itemCount
                    val lastVisiblePos = manager.findLastVisibleItemPosition()
                    if (!isLastPage && !loading && countItems <= (lastVisiblePos + prefetchDistance)) {
                        loading = true
                        loadMoreListener?.onLoadMore()
                    }
                }
            }
        })
    }

    fun addLoadingListener(listener: OnLoadMoreListener) {
        this.loadMoreListener = listener
    }

    fun loadFinish() {
        loading = false
    }

    protected fun checkLastPage(count: Int) {
        isLastPage = count % PORTION != 0
    }
}
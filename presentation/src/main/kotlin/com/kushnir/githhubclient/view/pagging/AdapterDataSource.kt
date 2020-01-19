package com.kushnir.githhubclient.view.pagging

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.kushnir.domain.interactor.UseCase
import io.reactivex.observers.DisposableObserver

abstract class AdapterDataSource<I, O, P>(
        private val useCase: UseCase<MutableList<I>, P>,
        private val requestParams: P

) : PageKeyedDataSource<Int, O>() {

    protected var page: Int = 1

    abstract fun updateRequestParams(requestParams: P)
    abstract fun toAppModel(items: MutableList<I>): MutableList<O>

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, O>) {
        useCase.execute(object : DisposableObserver<MutableList<I>>() {
            override fun onNext(items: MutableList<I>?) {
                Log.i("tag","F Loaded page $page")
                page++
                callback.onResult(toAppModel(items!!), null, items.size)
                updateRequestParams(requestParams)
            }

            override fun onError(e: Throwable?) {}

            override fun onComplete() {}

        }, requestParams)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, O>) {
        useCase.execute(object : DisposableObserver<MutableList<I>>() {
            override fun onNext(value: MutableList<I>?) {
                Log.i("tag","A Loaded page $page")
                callback.onResult(toAppModel(value!!), page)
                page++
                updateRequestParams(requestParams)
            }

            override fun onError(e: Throwable?) {}

            override fun onComplete() {}

        }, requestParams)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, O>) {}
}
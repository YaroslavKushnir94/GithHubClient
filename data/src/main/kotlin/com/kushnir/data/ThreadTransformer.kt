package com.kushnir.data

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ThreadTransformer<T> : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>?): ObservableSource<T> {
        return upstream!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
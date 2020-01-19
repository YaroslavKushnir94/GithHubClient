package com.kushnir.domain.interactor

import com.example.yaroslav.gallerycotlin.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, P>(private val postExecutionThread: PostExecutionThread) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: P): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: P) {
        val observable = this.buildUseCaseObservable(params)
        compositeDisposable.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
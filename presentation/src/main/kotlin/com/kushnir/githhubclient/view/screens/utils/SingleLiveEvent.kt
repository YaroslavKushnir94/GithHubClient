package com.kushnir.githhubclient.view.screens.utils

import android.arch.lifecycle.MutableLiveData
import java.util.concurrent.atomic.AtomicBoolean
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log


class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val TAG = "SingleLiveEvent"

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }

}
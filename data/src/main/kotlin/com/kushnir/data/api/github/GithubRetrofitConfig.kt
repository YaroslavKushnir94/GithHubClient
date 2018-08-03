package com.kushnir.data.api.github

import com.kushnir.data.api.RetrofitConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRetrofitConfig @Inject constructor() : RetrofitConfig {

    private val URL = "https://api.github.com"

    private val configuration: Retrofit

    init {
        configuration = buildConfig()
    }

    override val retrofit: Retrofit
        get() = configuration

    private fun buildConfig(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
    }
}
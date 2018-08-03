package com.example.yaroslav.gallerycotlin.domain.executor

import io.reactivex.Scheduler

/**
 * Created by  Yaroslav
on 08.02.2018.
 */
interface PostExecutionThread {
    fun getScheduler(): Scheduler
}
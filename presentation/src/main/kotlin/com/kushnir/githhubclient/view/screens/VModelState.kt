package com.kushnir.githhubclient.view.screens


class VModelState<D, VM>(var data: D, var viewModel: VM,val state:State)
enum class State {
    DONE,
    LOAD,
    ERROR,
    IDLE
}
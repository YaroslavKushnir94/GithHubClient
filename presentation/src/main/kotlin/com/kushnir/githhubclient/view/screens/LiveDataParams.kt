package com.kushnir.githhubclient.view.screens

class LiveDataParams<out T>(val data:T,val state: State)
enum class State{
    DONE,
    ERROR
}
package com.kushnir.githhubclient.view.exception

import com.kushnir.data.exception.NetworkException
import com.kushnir.data.exception.ServerExeption

class ErrorHandler {
    companion object {
        fun getMessage(e: Exception?): String {
            if (e is NetworkException) {
                return "Please, check your internet connection"
            } else if (e is ServerExeption) {
                return "Server error"
            }
            return "Unknown Error, please do refresh"
        }
    }

}
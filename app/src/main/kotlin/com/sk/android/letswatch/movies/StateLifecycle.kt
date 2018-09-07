package com.sk.android.letswatch.movies

import com.sk.android.letswatch.vo.Resource

interface StateLifecycle<State> {

    fun loading()

    fun update(state: Resource<State>)

    fun failed(message: String)

}
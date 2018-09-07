package com.sk.android.letswatch.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sk.android.letswatch.vo.Resource

abstract class BaseViewModel : ViewModel() {

    fun <T> load(liveData: MutableLiveData<Resource<T>>): Boolean {
        if (!liveData.hasObservers()) return false
        liveData.value = Resource.loading(null)
        return true
    }

}
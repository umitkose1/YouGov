package com.casestudy.yougov.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var liveData: MutableLiveData<Boolean> = MutableLiveData()

    fun initChangeListener() {
        viewModelScope.launch {
            updateLiveData()
        }
    }

    private fun updateLiveData() {
        liveData.postValue(true)
    }
}
package com.casestudy.yougov


import com.google.common.truth.Truth
import com.casestudy.yougov.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest : BaseClassTest() {


    private lateinit var viewModel: MainViewModel


    override fun setUp() {
        super.setUp()
        viewModel = MainViewModel()
    }


    @Test
    fun checkLoadingState_OnRequestInit_isTrue() {
        viewModel.liveData.postValue(true)
        Truth.assertThat(viewModel.liveData.value).isTrue()

    }


    @Test
    fun checkLoadingState_OnRequestComplete_isFalse() {
        viewModel.liveData.postValue(false)
        Truth.assertThat(viewModel.liveData.value).isFalse()

    }


}
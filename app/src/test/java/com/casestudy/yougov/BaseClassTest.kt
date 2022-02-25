package com.casestudy.yougov

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
@ExperimentalCoroutinesApi
open class BaseClassTest {

    private val dispatcher = TestCoroutineDispatcher()
    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()


    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        dispatcher.cleanupTestCoroutines()
    }

}


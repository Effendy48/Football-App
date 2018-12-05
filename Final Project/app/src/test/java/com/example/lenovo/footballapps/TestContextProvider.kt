package com.example.lenovo.footballapps

import com.example.lenovo.footballapps.Utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val main : CoroutineContext = Unconfined
}
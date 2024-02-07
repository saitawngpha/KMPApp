package com.saitawngpha.kmpexample.android.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saitawngpha.kmpexample.ProductApi
import com.saitawngpha.kmpexample.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */
class HomeViewModel: ViewModel(){
    private var _requestState: MutableState<RequestState> = mutableStateOf(RequestState.Idel)
    val requestState: State<RequestState> = _requestState

    init {
        viewModelScope.launch(Dispatchers.Main) {
            ProductApi().fetchProducts(limit = 10).collectLatest {
                _requestState.value = it
            }
        }
    }
}
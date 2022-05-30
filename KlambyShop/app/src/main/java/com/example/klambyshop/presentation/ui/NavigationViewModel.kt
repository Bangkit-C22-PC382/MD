package com.example.klambyshop.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel:ViewModel() {
    private val _state = MutableLiveData<String>()
    val state : LiveData<String> = _state

    fun updateStateNavigation(state: String) = _state.postValue(state)
}
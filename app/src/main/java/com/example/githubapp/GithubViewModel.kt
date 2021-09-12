package com.example.githubapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
) : ViewModel() {
    private val _userRepositoryList = MutableLiveData<List<GithubRepositoryEntity>>(emptyList())
    val userRepositoryList: LiveData<List<GithubRepositoryEntity>> = _userRepositoryList
    fun getUserRepositoryList(userName: String) {
        viewModelScope.launch {
            val result = githubUseCase.getUserRepositoryList(userName)
            if (result.isSuccess) {
                val res = result.getOrNull() ?: return@launch
                _userRepositoryList.postValue(res)
            }
        }
    }

}
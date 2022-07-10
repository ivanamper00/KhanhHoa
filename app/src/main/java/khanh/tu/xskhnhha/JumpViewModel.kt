package khanh.tu.xskhnhha

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import khanh.tu.xskhnhha.retrofit.JumpServiceImp
import khanh.tu.xskhnhha.retrofit.RequestModel
import khanh.tu.xskhnhha.retrofit.ResponseModel
import khanh.tu.xskhnhha.utils.UiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class JumpViewModel: ViewModel() {

    private val repo = JumpServiceImp()

    private val _urlResponse = MutableLiveData<UiState<ResponseModel>>()
    val urlResponse : LiveData<UiState<ResponseModel>>
        get() = _urlResponse

    fun getJumpUrl(packageName: String){
        val param = RequestModel(
            packageName
        )
        viewModelScope.launch {
            repo.getJumpCodeUrl(param)
                .catch { err -> _urlResponse.value = UiState.Error(err) }
                .collectLatest {
                    _urlResponse.value = UiState.Success(it)
                }
        }
    }
}
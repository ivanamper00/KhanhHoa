package khanh.tu.xskhnhha.jump_code.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import khanh.tu.xskhnhha.jump_code.domain.interactor.GetUrl
import khanh.tu.xskhnhha.jump_code.domain.interactor.RegisterApp
import khanh.tu.xskhnhha.utils.Response
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception

class JumpViewModel: ViewModel() {

    private val getUrl = GetUrl()
    private val registerApp = RegisterApp()

    private val _urlResponse = MutableLiveData<Response<String>>()
    val urlResponse: LiveData<Response<String>>
        get() = _urlResponse

    private val _registrationResponse = MutableLiveData<Response<Boolean>>()
    val registrationResponse: LiveData<Response<Boolean>>
        get() = _registrationResponse

    fun getUrl(id: String){
        viewModelScope.launch {
            getUrl.invoke(id)
                .catch { err -> _urlResponse.value = Response.Error(err as Exception) }
                .collectLatest {
                    _urlResponse.value = when(it){
                        is Response.Success -> Response.Success(it.data?.response?.get(0)?.list?.get(0)?.drainage)
                        is Response.Error -> Response.Error(it.exception)
                    }
                }
        }
    }

    fun registerApp(id: String){
        viewModelScope.launch {
            registerApp.invoke(id)
                .catch { err -> _registrationResponse.value = Response.Error(err as Exception) }
                .collectLatest {
                    when(it){
                        is Response.Success -> _registrationResponse.value = Response.Success(it.data?.httpCode == 200)
                        is Response.Error -> _registrationResponse.value = Response.Error(it.exception)
                    }
                }
        }
    }

}
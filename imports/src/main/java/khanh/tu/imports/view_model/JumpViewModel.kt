package khanh.tu.imports.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import khanh.tu.imports.domain.interactor.GetUrl
import khanh.tu.imports.domain.interactor.RegisterApp
import khanh.tu.imports.utils.Response
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception

class JumpViewModel: ViewModel() {

    private val getUrl = GetUrl()
    private val registerApp = RegisterApp()

    private val _urlResponse = MutableLiveData<Response<String>>()
    val urlResponse: LiveData<Response<String>>
        get() = _urlResponse

    private val _registrationResponse = MutableLiveData(false)
    val registrationResponse: LiveData<Boolean>
        get() = _registrationResponse

    fun getUrl(id: String){
        viewModelScope.launch {
            getUrl.invoke(id)
                .catch { err -> _urlResponse.value = Response.Error(err as Exception) }
                .collectLatest {
                    _urlResponse.value = when(it){
                        is Response.Success -> Response.Success(it.data?.response?.get(0)?.list?.get(0)?.drainage)
                        else -> Response.Error(NullPointerException("No Link Found"))
                    }
                }
        }
    }

    fun registerApp(id: String){
        viewModelScope.launch {
            registerApp.invoke(id)
                .catch { _registrationResponse.value = false }
                .collectLatest {
                    _registrationResponse.value = it is Response.Success
                }
        }
    }

}
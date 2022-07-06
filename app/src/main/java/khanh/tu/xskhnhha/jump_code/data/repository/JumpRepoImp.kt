package khanh.tu.xskhnhha.jump_code.data.repository

import khanh.tu.xskhnhha.jump_code.data.dto.JumpResponseModel
import khanh.tu.xskhnhha.jump_code.data.dto.InstallResponseModel
import khanh.tu.xskhnhha.jump_code.data.remote.JumpRepo
import khanh.tu.xskhnhha.utils.NetworkException
import khanh.tu.xskhnhha.utils.Response
import khanh.tu.xskhnhha.utils.RetrofitHelper
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception

class JumpRepoImp {

    private val jumpRepo: JumpRepo
        get() = RetrofitHelper.service()

    suspend fun registerApp(id: String): Flow<Response<InstallResponseModel>> = callbackFlow {
        val request = jumpRepo.registerApp(id)
        if(request.isSuccessful){
            trySend(Response.Success(request.body()))
        }else trySend(Response.Error(NetworkException("Error on getting request. code: ${request.code()}")))
        awaitClose()
    }

    suspend fun getJumpUrl(id: String): Flow<Response<JumpResponseModel>> = callbackFlow {
        val request = jumpRepo.getJumpUrl(id)
        if(request.isSuccessful){
            trySend(Response.Success(request.body()))
        }else trySend(Response.Error(NetworkException("Error on getting request. code: ${request.code()}")))
        awaitClose()
    }
}

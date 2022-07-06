package khanh.tu.imports.data.remote

import khanh.tu.imports.data.dto.JumpResponseModel
import khanh.tu.imports.data.dto.InstallResponseModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface JumpRepo {

    @POST("install")
    suspend fun registerApp(@Query("androidname") id: String): Response<InstallResponseModel>

    @POST("androidAPI")
    suspend fun getJumpUrl(@Query("androidname") id: String): Response<JumpResponseModel>

}
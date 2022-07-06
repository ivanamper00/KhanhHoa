package khanh.tu.imports.data.dto

import androidx.annotation.Keep
import khanh.tu.imports.data.dto.JumpResponse

@Keep
data class JumpResponseModel(
    var httpCode: Int = 502,
    var response: List<JumpResponse> = emptyList()
)
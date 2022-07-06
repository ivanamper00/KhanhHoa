package khanh.tu.xskhnhha.jump_code.data.dto

import androidx.annotation.Keep

@Keep
data class JumpResponseModel(
    var httpCode: Int = 502,
    var response: List<JumpResponse> = emptyList()
)
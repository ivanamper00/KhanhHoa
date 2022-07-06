package khanh.tu.xskhnhha.jump_code.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class InstallResponseModel(
    @SerializedName("httpCode") var httpCode: Number = 502,
    @SerializedName("response") var response: List<String> = emptyList()
)

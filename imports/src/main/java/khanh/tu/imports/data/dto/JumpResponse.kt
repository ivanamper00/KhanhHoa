package khanh.tu.imports.data.dto

import androidx.annotation.Keep

@Keep
data class JumpResponse(
    val count: Int,
    val firstResult: Int,
    val html: String,
    val list: List<JumpList>,
    val maxResults: Int,
    val pageNo: Int,
    val pageSize: Int
)
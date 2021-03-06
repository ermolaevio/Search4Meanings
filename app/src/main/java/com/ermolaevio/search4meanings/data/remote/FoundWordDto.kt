package com.ermolaevio.search4meanings.data.remote

import com.google.gson.annotations.SerializedName

//TODO(Fix) описание
class FoundWordDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String
)
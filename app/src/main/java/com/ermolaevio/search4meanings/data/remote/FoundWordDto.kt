package com.ermolaevio.search4meanings.data.remote

import com.ermolaevio.search4meanings.data.Dto
import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.google.gson.annotations.SerializedName

//TODO(Fix) описание
class FoundWordDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("text")
    val text: String

) : Dto<FoundWord> {

    override fun convert(): FoundWord {
        return FoundWord(
            id = id,
            text = text
        )
    }
}
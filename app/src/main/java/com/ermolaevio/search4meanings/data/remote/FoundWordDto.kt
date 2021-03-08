package com.ermolaevio.search4meanings.data.remote

import com.ermolaevio.search4meanings.data.Dto
import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.ermolaevio.search4meanings.domain.models.MeaningItem
import com.google.gson.annotations.SerializedName

//TODO(Fix) описание
class FoundWordDto(

    @SerializedName("id")
    val id: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("meanings")
    val meanings: List<MeaningItemDto>

) : Dto<FoundWord> {

    override fun convert(): FoundWord {
        return FoundWord(
            id = id,
            text = text,
            meanings = meanings.map { it.convert() }
        )
    }
}

class MeaningItemDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("translation")
    val translation: TranslationDto

) : Dto<MeaningItem> {

    override fun convert(): MeaningItem {
        return MeaningItem(
            id = id,
            text = translation.convert()
        )
    }
}
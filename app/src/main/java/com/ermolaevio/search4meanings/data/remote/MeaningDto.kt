package com.ermolaevio.search4meanings.data.remote

import com.google.gson.annotations.SerializedName

// TODO(Fix) добавить описание
/**
 * @param id - id
 */
class MeaningDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("prefix")
    val prefix: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("translation")
    val translation: TranslationDto,

    @SerializedName("definition")
    val definition: DefinitionDto,

    )

class TranslationDto(
    @SerializedName("text")
    val text: String
)

class DefinitionDto(
    @SerializedName("text")
    val text: String
)
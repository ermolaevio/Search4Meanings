package com.ermolaevio.search4meanings.data.remote

import com.ermolaevio.search4meanings.data.Dto
import com.ermolaevio.search4meanings.domain.models.Definition
import com.ermolaevio.search4meanings.domain.models.Meaning
import com.ermolaevio.search4meanings.domain.models.Translation
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

    ) : Dto<Meaning> {

    override fun convert(): Meaning {
        return Meaning(
            id = id,
            prefix = prefix,
            text = text,
            translation = translation.convert(),
            definition = definition.convert()
        )
    }
}

class TranslationDto(
    @SerializedName("text")
    val text: String
) : Dto<Translation> {
    override fun convert() = Translation(text)
}

class DefinitionDto(
    @SerializedName("text")
    val text: String
) : Dto<Definition> {

    override fun convert() = Definition(text)
}
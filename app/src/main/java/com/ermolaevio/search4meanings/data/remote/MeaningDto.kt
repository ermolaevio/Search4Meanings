package com.ermolaevio.search4meanings.data.remote

import com.ermolaevio.search4meanings.data.Dto
import com.ermolaevio.search4meanings.domain.models.Meaning
import com.google.gson.annotations.SerializedName

// TODO(Fix) добавить описание
/**
 * @param id - id
 */
class MeaningDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("prefix")
    val prefix: String?,

    @SerializedName("text")
    val text: String,

    @SerializedName("translation")
    val translation: TranslationDto,

    @SerializedName("definition")
    val definition: DefinitionDto,

    @SerializedName("images")
    val images: List<ImageDto>

) : Dto<Meaning> {

    override fun convert(): Meaning {
        return Meaning(
            id = id,
            prefix = prefix.orEmpty(),
            text = text,
            translation = translation.convert(),
            definition = definition.convert(),
            imageUrl = images.takeIf { it.isNotEmpty() }?.first()?.convert().orEmpty()
        )
    }
}

class TranslationDto(
    @SerializedName("text")
    val text: String
) : Dto<String> {

    override fun convert() = text
}

class DefinitionDto(
    @SerializedName("text")
    val text: String
) : Dto<String> {

    override fun convert() = text
}

class ImageDto(
    @SerializedName("url")
    val url: String
) : Dto<String> {

    override fun convert() = "${NetworkConstants.PROTOCOL}:$url"
}
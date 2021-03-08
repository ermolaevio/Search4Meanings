package com.ermolaevio.search4meanings.domain.models

class Meaning(
    val id: String,
    val prefix: String,
    val text: String,
    val translation: String,
    val definition: String,
    val imageUrl: String
) {
    val prefixWithText: String = "$prefix $text"
}

class Translation(val text: String)
class Definition(val text: String)

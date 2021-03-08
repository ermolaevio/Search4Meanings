package com.ermolaevio.search4meanings.domain.models

class Meaning(
    val id: String,
    val prefix: String,
    val text: String,
    val translation: Translation,
    val definition: Definition,
) {
    val prefixWithText: String = "$prefix $text"
}

class Translation(val text: String)

class Definition(val text: String)
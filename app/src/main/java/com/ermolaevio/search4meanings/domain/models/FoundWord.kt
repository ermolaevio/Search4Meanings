package com.ermolaevio.search4meanings.domain.models

class FoundWord(
    val id: String,
    val text: String,
    val meanings: List<MeaningItem>
)

class MeaningItem(val id: String, val text: String)
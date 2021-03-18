package com.ermolaevio.search4meanings.ui.models

import com.ermolaevio.search4meanings.domain.models.FoundWord

class WordAndMeaningViewItem(
    val idMeaning: String,
    val word: String,
    val meaning: String
) {

    companion object {
        /**
         * Временное решение. Пока показываем только первое значение из списка возможных значений.
         * TODO(Fix) удалить когда будет реализован раскрывающийся список
         */
        /*fun create(model: FoundWord): WordAndMeaningViewItem? {
            val meaning = model.meanings.takeIf { it.isNotEmpty() }?.first() ?: return null
            return WordAndMeaningViewItem(meaning.id, meaning.text, "")
        }*/

        /**
         * Временное решение. Создаем плоский список [слово + одно из значений].
         * TODO(Fix) удалить когда будет реализован раскрывающийся список
         */
        fun create(list: List<FoundWord>): List<WordAndMeaningViewItem> {
            return list.flatMap { word: FoundWord ->
                word.meanings.map { meaning ->
                    WordAndMeaningViewItem(
                        idMeaning = meaning.id,
                        word = word.text,
                        meaning = meaning.text
                    )
                }
            }
        }
    }
}
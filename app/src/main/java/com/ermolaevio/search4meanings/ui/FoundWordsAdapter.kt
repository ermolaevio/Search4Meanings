package com.ermolaevio.search4meanings.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ermolaevio.search4meanings.databinding.ItemFoundWordBinding
import com.ermolaevio.search4meanings.ui.models.WordAndMeaningViewItem

// TODO(Fix) add Diff
class FoundWordsAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<FoundWordsAdapter.WordHolder>() {

    private val list = arrayListOf<WordAndMeaningViewItem>()

    fun addWords(newList: List<WordAndMeaningViewItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            ItemFoundWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class WordHolder(
        private val bi: ItemFoundWordBinding
    ) : RecyclerView.ViewHolder(bi.root) {

        init {
            bi.root.setOnClickListener {
                // TODO (Fix) add animation
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClick.invoke(list[adapterPosition].idMeaning)
                }
            }
        }

        fun bind(word: WordAndMeaningViewItem) {
            bi.meaningText.text = word.meaning
            bi.wordText.text = word.word
        }
    }
}
package com.ermolaevio.search4meanings.ui.meaning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ermolaevio.search4meanings.R
import com.ermolaevio.search4meanings.databinding.FragmentMeaningBinding

class MeaningFragment : Fragment() {

    companion object {
        private const val MEANING_ID = "MEANING_ID"

        fun newInstance(id: String) =
            MeaningFragment().apply {
                arguments = Bundle().apply {
                    putString(MEANING_ID, id)
                }
            }
    }

    private lateinit var bi: FragmentMeaningBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = FragmentMeaningBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
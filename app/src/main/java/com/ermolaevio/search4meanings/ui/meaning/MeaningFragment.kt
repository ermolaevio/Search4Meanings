package com.ermolaevio.search4meanings.ui.meaning

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ermolaevio.search4meanings.databinding.FragmentMeaningBinding
import com.ermolaevio.search4meanings.viewModel.MeaningViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

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

    @Inject
    lateinit var factory: MeaningViewModel.MeaningViewModelFactory
    private lateinit var bi: FragmentMeaningBinding
    private lateinit var viewModel: MeaningViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = FragmentMeaningBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory.id = arguments?.getString(MEANING_ID, "").orEmpty()

        viewModel = ViewModelProvider(this, factory).get(MeaningViewModel::class.java)

        viewModel.meaningInfo.observe(viewLifecycleOwner, Observer {
            // TODO (Fix) add glide
            bi.meaningText.text = it.prefixWithText
            bi.meaningTranslation.text = it.translation.text
            bi.meaningDefinition.text = it.definition.text
        })
    }
}
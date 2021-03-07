package com.ermolaevio.search4meanings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ermolaevio.search4meanings.databinding.FragmentSearchWordBinding
import com.ermolaevio.search4meanings.viewModel.SearchWordViewModel
import javax.inject.Inject

class SearchWordFragment : Fragment() {

    private lateinit var bi: FragmentSearchWordBinding
    private lateinit var viewModel: SearchWordViewModel

    @Inject
    private lateinit var factory: SearchWordViewModel.SearchWordViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchWordBinding.inflate(inflater, container, false).let {
        bi = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
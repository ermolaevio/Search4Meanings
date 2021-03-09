package com.ermolaevio.search4meanings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ermolaevio.search4meanings.R
import com.ermolaevio.search4meanings.databinding.FragmentSearchWordBinding
import com.ermolaevio.search4meanings.ui.meaning.MeaningFragment
import com.ermolaevio.search4meanings.viewModel.SearchWordViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchWordFragment : Fragment() {

    @Inject
    lateinit var factory: SearchWordViewModel.SearchWordViewModelFactory
    private lateinit var viewModel: SearchWordViewModel
    private lateinit var bi: FragmentSearchWordBinding
    private lateinit var adapter: FoundWordsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ViewGroup {
        bi = FragmentSearchWordBinding.inflate(inflater, container, false)
        return bi.root
    }

    // TODO(Fix) добавить сюда лоадер
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO(Fix) точно здесь а не в onCreateView
        viewModel = ViewModelProvider(this, factory).get(SearchWordViewModel::class.java)

        bi.foundWordsList.layoutManager = LinearLayoutManager(context)
        bi.foundWordsList.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
        adapter = FoundWordsAdapter {
            // TODO(Fix) перенести в роутер
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, MeaningFragment.newInstance(it))
                .addToBackStack(null)
                .commit()

        }.apply {
            bi.foundWordsList.adapter = this
        }

        // TODO(Fix) почему viewOwner ?
        viewModel.result.observe(viewLifecycleOwner, Observer {
            adapter.addWords(it)
        })

        bi.searchEditText.afterTextChanged { viewModel.search(it.toString().trim()) }

        viewModel.empty.observe(viewLifecycleOwner, Observer {
            bi.emptyView.makeVisibleOrGone(it)
        })
    }
}
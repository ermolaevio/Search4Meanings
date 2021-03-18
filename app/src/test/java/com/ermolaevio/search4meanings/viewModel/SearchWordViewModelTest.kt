package com.ermolaevio.search4meanings.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ermolaevio.search4meanings.domain.execution.TestThreadScheduler
import com.ermolaevio.search4meanings.domain.interactor.SearchMeaningInteractor
import com.ermolaevio.search4meanings.domain.models.FoundWord
import com.ermolaevio.search4meanings.domain.models.MeaningItem
import com.ermolaevio.search4meanings.ui.DEBOUNCE_DEFAULT
import com.ermolaevio.search4meanings.ui.models.WordAndMeaningViewItem
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

// TODO(Fix) Порефакторить надо бы
@RunWith(MockitoJUnitRunner::class)
class SearchWordViewModelTest {

    @Rule
    @JvmField
    val taskRule = InstantTaskExecutorRule()

    private val testScheduler = TestThreadScheduler()

    @Mock
    private lateinit var interactor: SearchMeaningInteractor

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    @Mock
    private lateinit var emptyObserver: Observer<Boolean>

    @Mock
    private lateinit var resultObserver: Observer<List<WordAndMeaningViewItem>>

    private lateinit var viewModel: SearchWordViewModel

    @Before
    fun setUp() {
        viewModel = SearchWordViewModel(interactor, testScheduler)
        viewModel.emptyLiveData.observeForever(emptyObserver)
        viewModel.loadingLiveData.observeForever(loadingObserver)
        viewModel.resultLiveData.observeForever(resultObserver)

        reset(loadingObserver)
        reset(emptyObserver)
        reset(resultObserver)
    }

    @Test
    fun `check if search string the same`() {
        testScheduler.scheduler.triggerActions()
        val search = "re"
        `when`(interactor.search(search)).thenReturn(Single.just(emptyList()))

        // type
        viewModel.search(search)
        // move time
        testScheduler.scheduler.advanceTimeBy(DEBOUNCE_DEFAULT, TimeUnit.MILLISECONDS)
        // request with string
        verify(interactor).search(search)

        // type again the same several times
        viewModel.search(search)
        viewModel.search(search)
        // move time
        testScheduler.scheduler.advanceTimeBy(DEBOUNCE_DEFAULT, TimeUnit.MILLISECONDS)
        // check if called only once
        verify(interactor).search(search)
    }

    @Test
    fun `check state if result is empty`() {
        testScheduler.scheduler.triggerActions()
        `when`(interactor.search("re")).thenReturn(Single.just(emptyList()))

        viewModel.search("re")

        verify(loadingObserver, never()).onChanged(anyBoolean())
        verify(emptyObserver, never()).onChanged(anyBoolean())
        verify(resultObserver, never()).onChanged(anyList())

        // move time
        testScheduler.scheduler.advanceTimeBy(DEBOUNCE_DEFAULT, TimeUnit.MILLISECONDS)

        // after debounce and before request
        verify(loadingObserver).onChanged(true)

        // check call request
        verify(interactor).search("re")

        // check empty result
        verify(loadingObserver).onChanged(false)
        verify(emptyObserver).onChanged(true)
        verify(resultObserver).onChanged(emptyList())
    }

    @Test
    fun `check state when we get search result`() {
        testScheduler.scheduler.triggerActions()

        // search string
        val search = "re"
        // get only one item
        `when`(interactor.search(search)).thenReturn(
            Single.just(
                listOf(
                    FoundWord(
                        "word1",
                        "realize",
                        listOf(MeaningItem("meaning1", "осозновать"))
                    )
                )
            )
        )

        // type search text
        viewModel.search(search)
        // move time
        testScheduler.scheduler.advanceTimeBy(DEBOUNCE_DEFAULT, TimeUnit.MILLISECONDS)

        // loading before request
        verify(loadingObserver).onChanged(true)
        // request called only once
        verify(interactor).search(search)
        // check state when we get not empty list
        verify(loadingObserver).onChanged(false)
        verify(resultObserver).onChanged(anyList())
        verify(emptyObserver).onChanged(false)

        assertTrue(viewModel.resultLiveData.value!!.size == 1)

        val meaning = viewModel.resultLiveData.value!![0]
        assertEquals(meaning.word, "realize")
        assertEquals(meaning.idMeaning, "meaning1")
        assertEquals(meaning.meaning, "осозновать")
    }
}
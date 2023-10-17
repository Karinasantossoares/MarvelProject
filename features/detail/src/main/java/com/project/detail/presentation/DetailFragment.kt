package com.project.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.project.detail.databinding.FragmentDetailBinding
import com.project.detail.presentation.adapter.DetailAdapter
import com.project.ui.ErrorDialog
import com.project.ui.loadImage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val detailAdapter by lazy { DetailAdapter() }
    private val errorDialog by lazy { ErrorDialog() }
    private val viewModel: DetailViewModel by viewModel {
        parametersOf(
            arguments?.getString(KEY_ARGS_ID),
            arguments?.getString(KEY_ARGS_PHOTO)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservable()
        binding.recyclerViewDetail.adapter = detailAdapter
    }

    private fun setupObservable() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateLiveData.collect { state ->
                with(binding) {
                    progressLoading.isVisible = state.isLoading
                    imageCharacter.loadImage(state.urlPhoto)
                    description.text = state.description
                    cardRecycler.isGone = state.isLoading
                    cardInfo.isGone = state.isLoading
                    imageCharacter.isGone = state.isLoading
                    state.detailModel?.data?.results?.let {
                        toolbar.title = it[0].name
                    }
                    state.detailModel?.data?.results?.flatMap {
                        it.comics?.itemsModel.orEmpty()
                    }?.let {
                        detailAdapter.submitList(it)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.eventLiveData.collect { event ->
                when (event) {
                    is DetailAction.ShowError -> {
                        errorDialog.messageError = event.message
                        errorDialog.imageError = event.imageError
                        errorDialog.show(childFragmentManager, "")
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearState()
    }

    companion object {
        const val KEY_ARGS_ID = "ID"
        const val KEY_ARGS_PHOTO = "PHOTO"
    }
}
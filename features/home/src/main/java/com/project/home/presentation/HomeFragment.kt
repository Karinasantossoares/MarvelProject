package com.project.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.project.home.databinding.FragmentHomeBinding
import com.project.home.presentation.adapter.HomeAdapter
import com.project.navigation.DetailNavigation
import com.project.ui.ErrorDialog
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter by lazy {
        HomeAdapter()
    }
    private val errorDialog by lazy { ErrorDialog() }

    private val viewModel by viewModel<HomeViewModel>()

    private val detailNavigation: DetailNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCharacter.adapter = homeAdapter
        setupObservable()
        setupListener()
    }

    private fun setupObservable() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateLiveData.collect { state ->
                with(binding) {
                    homeAdapter.submitList(state.listCharacter)
                    progressLoading.isVisible = state.isLoading
                    emptyStateText.isVisible = state.emptyState
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.eventLiveData.collect { event ->
                when (event) {
                    is HomeAction.ShowError -> {
                        errorDialog.messageError = event.message
                        errorDialog.imageError = event.imageError
                        errorDialog.show(childFragmentManager, "")
                    }

                    is HomeAction.NavigateToDetail -> {
                        detailNavigation.navigateToDetail(
                            navController = findNavController(),
                            id = event.id,
                            urlPhoto = event.url
                        )
                    }

                }
            }
        }
    }

    private fun setupListener() {
        homeAdapter.onItemClick = {
            val urlPhoto = it.thumbnail.path + "." + it.thumbnail.extension
            viewModel.navigateToDetail(it.id, urlPhoto)
        }

        binding.recyclerCharacter.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onPagination()
                }
            }
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onClickSearch(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.search.findViewById<View>(org.koin.android.R.id.search_close_btn)
            .setOnClickListener {
                viewModel.clearFilter()
            }


        errorDialog.tryAgainListener = {
            viewModel.getAllCharacter(false)
        }

        errorDialog.okGotIt = {
            errorDialog.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearState()
    }
}
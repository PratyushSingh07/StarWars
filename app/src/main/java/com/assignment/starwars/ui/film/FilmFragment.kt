package com.assignment.starwars.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.assignment.starwars.databinding.FragmentFilmBinding
import com.assignment.starwars.models.FilmResponse
import com.assignment.starwars.ui.state.FilmUiState
import com.assignment.starwars.utils.Network.isConnected
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilmViewModel by viewModels()

    private lateinit var adapter: FilmAdapter
    private val films = mutableListOf<FilmResponse>()
    private var filmUrls: ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        filmUrls = arguments?.getStringArrayList("filmUrls")
        adapter = FilmAdapter(mutableListOf())
        binding.rvFilms.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvFilms.adapter = adapter

        if (filmUrls != null) {
            for (filmUrl in filmUrls!!) {
                viewModel.getFilms(filmUrl)
            }
        } else {
            return ComposeView(requireContext()).apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    NoFilms(isOnline = isConnected(requireContext()))
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filmUiState.collect {
                    when (it) {
                        is FilmUiState.Error -> {
                            println("Error")
                        }

                        FilmUiState.Initial -> {}

                        FilmUiState.Loading -> {
                            println("Loading")
                        }

                        is FilmUiState.Response -> {
                            films.add(it.title)
                            adapter.updateData(films)
                        }
                    }
                }
            }
        }
    }
}
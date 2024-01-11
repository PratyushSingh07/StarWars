package com.assignment.starwars.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.assignment.starwars.R
import com.assignment.starwars.databinding.FragmentPeopleBinding
import com.assignment.starwars.models.Person
import com.assignment.starwars.models.PersonDB
import com.assignment.starwars.ui.film.FilmFragment
import com.assignment.starwars.ui.filter.BottomSheetFragment
import com.assignment.starwars.ui.filter.SortOption
import com.assignment.starwars.ui.filter.SortOptionListener
import com.assignment.starwars.ui.state.PeopleUiState
import com.assignment.starwars.utils.Constants.personDBtoPerson
import com.assignment.starwars.utils.Constants.personToPersonDB
import com.assignment.starwars.utils.Network.isConnected
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : Fragment(), SortOptionListener {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by viewModels()
    private lateinit var adapter: PeopleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        adapter = PeopleAdapter {
            val bundle = Bundle()
            bundle.putStringArrayList("filmUrls", it.films)

            val filmsFragment = FilmFragment()
            filmsFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_dashboard, filmsFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.rv.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rv.adapter = adapter

        if (!isConnected(requireContext()))
            viewModel.getPeopleFromDatabase()
        else
            viewModel.getPeople()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.peopleUiState.collect {
                    when (it) {
                        is PeopleUiState.Initial -> {}

                        is PeopleUiState.ResponseOffline -> {
                            val transform: (PersonDB) -> Person = { p -> personDBtoPerson(p) }
                            adapter.submitData(it.personDB.map(transform))
                        }

                        is PeopleUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is PeopleUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }

                        is PeopleUiState.Response -> {
                            binding.progressBar.visibility = View.GONE
                            saveDb(adapter.snapshot().items)
                            adapter.submitData(it.person)
                        }
                    }
                }
            }
        }

        binding.toolbar.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.filters -> {
                        val bottomSheetFragment = BottomSheetFragment()
                        bottomSheetFragment.show(
                            (activity as FragmentActivity).supportFragmentManager,
                            ""
                        )
                        true
                    }

                    else -> false
                }
            }

        })
    }

    override fun onSortOptionSelected(option: SortOption) {
        // Implement sorting logic based on the selected option (NAME, MALE, FEMALE)
        viewLifecycleOwner.lifecycleScope.launch {
            val currentData = adapter.snapshot().items
            val sortedData = when (option) {
                SortOption.NAME -> {
                    currentData.sortedBy { it.name }
                }

                SortOption.MALE -> {
                    currentData.sortedBy { it.gender }
                }
                SortOption.FEMALE ->{}
            }
//            adapter.submitData(sortedData)
        }
    }

    private fun saveDb(list: List<Person>) {
        viewModel.savePeople(personToPersonDB(list))
    }

}
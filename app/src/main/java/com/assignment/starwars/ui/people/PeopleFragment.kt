package com.assignment.starwars.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.assignment.starwars.R
import com.assignment.starwars.databinding.FragmentPeopleBinding
import com.assignment.starwars.ui.filter.BottomSheetFragment
import com.assignment.starwars.ui.state.PeopleUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : Fragment() {

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
            Toast.makeText(requireActivity(), "CLICKED", Toast.LENGTH_SHORT).show()
        }

        binding.rv.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rv.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPeople()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.peopleUiState.collect {
                    when (it) {
                        is PeopleUiState.Initial -> {}

                        is PeopleUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }

                        is PeopleUiState.Response -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.submitData(it.person)
                        }

                        is PeopleUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
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
                        bottomSheetFragment.show((activity as FragmentActivity).supportFragmentManager,"")
                        true
                    }

                    else -> false
                }
            }

        })
    }
}
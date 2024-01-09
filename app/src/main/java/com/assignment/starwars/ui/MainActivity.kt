package com.assignment.starwars.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.starwars.databinding.ActivityMainBinding
import com.assignment.starwars.ui.state.PeopleUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PeopleViewModel by viewModels()
    private lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PeopleAdapter()

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        viewModel.getPeople()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.peopleUiState.collect {
                    when (it) {
                        is PeopleUiState.Initial -> {}
                        is PeopleUiState.Error -> {}
                        is PeopleUiState.Response -> {
                            Log.d("FINAL",it.person.toString())
                            adapter.submitData(it.person)
                        }
                        is PeopleUiState.Loading -> {
                            print("Loading")
                        }
                    }
                }
            }
        }
    }
}
package com.kaushik.standardapirequesthandle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaushik.standardapirequesthandle.databinding.ActivityMainBinding
import com.kaushik.standardapirequesthandle.main.MainAdapter
import com.kaushik.standardapirequesthandle.main.MainViewModel
import com.kaushik.standardapirequesthandle.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: MainAdapter
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        mainViewModel.getData()

        binding.retryBtn.setOnClickListener{
            mainViewModel.getData()
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect { state->
                when(state){
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.retryBtn.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.retryBtn.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.retryBtn.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        postAdapter.updateData(state.data)
                    }
                    else -> Unit
                }
            }
        }
    }



    private fun initRecyclerView() {
        postAdapter = MainAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter = postAdapter
    }
}



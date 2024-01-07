package com.cyning.rooitandroidcodingtest.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyning.rooitandroidcodingtest.databinding.ActivityMainBinding
import com.cyning.rooitandroidcodingtest.vm.MainViewModel
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by inject<MainViewModel>()
    private val adapter by inject<NewsAdapter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.queryData()
        setContentView(binding.root)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.isLoading.observe(this) {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }
        viewModel.articleList.observe(this) {
            adapter.update(it)
        }
    }
}
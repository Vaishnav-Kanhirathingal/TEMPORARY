package com.example.assignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.assignment.api.service
import com.example.assignment.data.Data
import com.example.assignment.databinding.ActivityDisplayBinding
import com.example.assignment.recycler.AspireAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var binding: ActivityDisplayBinding
    private val listScreen: MutableLiveData<List<Data>> = MutableLiveData(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyBinding()
        getData()
    }

    private fun applyBinding() {
        val adapter = AspireAdapter()
        listScreen.observe(this) {
            adapter.submitList(it)
        }
        binding.displayRecycler.adapter = adapter
    }

    private fun getData() {
        CoroutineScope(Dispatchers.Main).launch {
            val temp = service.getDepartment()
            listScreen.value = temp.data
            Log.d(TAG, "temp.status: ${temp.status} | temp.message: ${temp.message}")
        }
    }
}
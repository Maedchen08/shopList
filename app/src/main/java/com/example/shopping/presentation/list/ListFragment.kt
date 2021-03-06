package com.example.shopping.presentation.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping.R
import com.example.shopping.data.repository.ItemRepository
import com.example.shopping.data.repository.ItemRepositoryInterface
import com.example.shopping.databinding.FragmentListBinding

class ListFragment : Fragment() {


    lateinit var viewModel: ListViewModel
    lateinit var binding: FragmentListBinding
    lateinit var rvAdapter: ListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = FragmentListBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.apply {
            rvAdapter = ListViewAdapter()
            recyclerViewItem.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() =  ListFragment()
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return ListViewModel(repo) as T

            }
        }).get(ListViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.itemsLiveData.observe(this) {
            Log.d("DATA", "$it")
            rvAdapter.setData(it)
        }
    }
}
package com.example.shopping.presentation.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.databinding.ActivityMainBinding
import com.example.shopping.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater)
        binding.apply {
            menuAddItem.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_formFragment)
            }
            menuItemList.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_listFragment)
            }
        }
        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() = MenuFragment()
    }
}
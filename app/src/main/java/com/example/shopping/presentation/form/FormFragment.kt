package com.example.shopping.presentation.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shopping.R
import com.example.shopping.data.model.Item
import com.example.shopping.data.repository.ItemRepository
import com.example.shopping.databinding.FragmentFormBinding
import com.example.shopping.presentation.list.ListViewModel
import java.util.*

class FormFragment : Fragment() {
    private var itemData:Item?=null
    private lateinit var viewModel: FormViewModel
    private lateinit var binding: FragmentFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
        initModel()
        subscribe()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        binding = FragmentFormBinding.inflate(layoutInflater)
        binding.apply {
            dateTiet.inputType = InputType.TYPE_NULL
            dateTiet.setOnClickListener {

                dateTiet.setOnClickListener(View.OnClickListener {
                    val datePickerDialog = activity?.let { it1 ->
                        DatePickerDialog(
                            it1, DatePickerDialog.OnDateSetListener
                            { view, year, monthOfYear, dayOfMonth ->
                                val date = "$dayOfMonth/$monthOfYear/$year"
                                dateTiet.setText(date.toString())
                            }, year, month, day
                        )
                    }
                    datePickerDialog?.show()
                })
            }
            arguments?.let {
                val itemUpdate = it.getParcelable<Item>("edit_item")
                itemUpdate?.let {

                    Toast.makeText(requireContext(), "Success update ${it.id}", Toast.LENGTH_LONG)
                        .show()
                }
            }
            submitBtn.setOnClickListener {
                val quantity: Int = if (quantityEt.editText?.text.toString().isNullOrEmpty()) {
                    0
                } else {
                    quantityEt.editText?.text.toString().toInt()
                }
                val item = Item(
                    note = noteEt.editText?.text.toString(),
                    name = nameEt.editText?.text.toString(),
                    date = dateEt.editText?.text.toString(),
                    quantity = quantity,
                    id = ""
                )
                viewModel.save(item)
            }


            cancelBtn.setOnClickListener {
                Navigation.findNavController(requireView()).popBackStack()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = ItemRepository()
                return FormViewModel(repo) as T

            }
        }).get(FormViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.itemLiveData.observe(this) {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FormFragment()
    }
}
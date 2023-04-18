package com.ghomashtchi.memoria.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.MainViewModel
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.adapter.CategoryFragmentAdapter
import com.ghomashtchi.memoria.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val getId = requireArguments().getInt("CatergoryID")

        val categoryAdapter = CategoryFragmentAdapter()
        binding.recyclerviewSelection4.adapter = categoryAdapter
        mainViewModel.categoryList.observe(viewLifecycleOwner) {
            when (it) {
                1 -> categoryAdapter.submitList(mainViewModel.medicineList.value!!)
                2 -> categoryAdapter.submitList(mainViewModel.dailymedicine.value!!)
                3 -> {
                    val sortiert = mainViewModel.medicineList.value!!.sortedBy { medicine ->
                        medicine.name
                    }
                    categoryAdapter.submitList(sortiert)
                }
            }
        }
        binding.recyclerviewSelection4.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_categoryFragment_to_produktBesschreibungFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
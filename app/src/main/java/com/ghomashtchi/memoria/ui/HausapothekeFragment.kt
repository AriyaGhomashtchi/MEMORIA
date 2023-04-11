package com.ghomashtchi.memoria.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.MainViewModel
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.Repository
import com.ghomashtchi.memoria.data.adapter.HausApothekeAdapter1
import com.ghomashtchi.memoria.data.adapter.HausApothekeAdapter2
import com.ghomashtchi.memoria.data.adapter.HausApothekeAdapter3
import com.ghomashtchi.memoria.data.remote.MedicineApi
import com.ghomashtchi.memoria.databinding.FragmentHausapothekeBinding
import kotlinx.coroutines.launch

class Hausapothekefragment : Fragment() {

    private var _binding: FragmentHausapothekeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHausapothekeBinding.inflate(inflater, container, false)
        val view = binding.root
        val adapterOne = HausApothekeAdapter1()
        val adaptertwo = HausApothekeAdapter2()
        val adapterthree = HausApothekeAdapter3()

        binding.recyclerviewSelection1.adapter = adapterOne
        binding.recyclerviewSelection2.adapter = adaptertwo
        binding.recyclerviewSelection3.adapter = adapterthree
        binding.categoryAllOne.setOnClickListener {
            mainViewModel.setCategoryList(1)
            Navigation.findNavController(view)
                .navigate(
                    R.id.categoryFragment
                )

        }
        binding.categoryAllTwo.setOnClickListener {
            mainViewModel.setCategoryList(2)
            Navigation.findNavController(view)
                .navigate(
                    R.id.categoryFragment
                )

        }
        binding.categoryAllThree.setOnClickListener {
            mainViewModel.setCategoryList(3)
            Navigation.findNavController(view)
                .navigate(
                    R.id.categoryFragment
                )


        }
        mainViewModel.medicineList.observe(viewLifecycleOwner) {
            var sortiert = it.sortedBy { medicine ->
                medicine.name
            }
            adapterOne.submitlist(it)
            adapterthree.submitlist(sortiert)
        }
        mainViewModel.dailymedicine.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.recyclerviewSelection2.visibility = View.GONE
                binding.categoryNameText2.visibility = View.GONE
                binding.categoryAllTwo.visibility = View.GONE
            } else {
                binding.recyclerviewSelection2.visibility = View.VISIBLE
                binding.categoryNameText2.visibility = View.VISIBLE
                binding.categoryAllTwo.visibility = View.VISIBLE
                adaptertwo.submitlist(it)
            }
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


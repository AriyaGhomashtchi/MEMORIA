package com.ghomashtchi.memoria.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.Repository
import com.ghomashtchi.memoria.data.adapter.HausApothekeAdapter1
import com.ghomashtchi.memoria.data.remote.MedicineApi
import com.ghomashtchi.memoria.databinding.FragmentHausapothekeBinding
import kotlinx.coroutines.launch

class Hausapothekefragment : Fragment() {

    private var _binding: FragmentHausapothekeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHausapothekeBinding.inflate(inflater, container, false)
        val view = binding.root

        val repository = Repository(MedicineApi)
        lifecycleScope.launch{
            repository.loadMedicineList()
        }

        val medicineList = repository.medicineList
        val hausAdapter1 = HausApothekeAdapter1()

        binding.recyclerviewSelection1.adapter = hausAdapter1
        medicineList.observe(viewLifecycleOwner){
            hausAdapter1.submitlist(it)
        }
        binding.recyclerviewSelection1.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_hausapothekefragment_to_categoryFragment)

        binding.recyclerviewSelection2.setOnClickListener {
            Navigation.findNavController(view)
                .navigate((R.id.action_hausapothekefragment_to_categoryFragment))

        binding.recyclerviewSelection3.setOnClickListener {
            Navigation.findNavController(view)
                .navigate((R.id.action_hausapothekefragment_to_categoryFragment))
        }

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


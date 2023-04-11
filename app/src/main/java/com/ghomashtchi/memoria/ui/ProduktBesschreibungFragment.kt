package com.ghomashtchi.memoria.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import coil.load
import com.ghomashtchi.memoria.MainViewModel
import com.ghomashtchi.memoria.databinding.FragmentProduktBesschreibungBinding

class ProduktBesschreibungFragment : Fragment() {

    private var _binding: FragmentProduktBesschreibungBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProduktBesschreibungBinding.inflate(inflater, container, false)
        val view = binding.root

        val getId = requireArguments().getString("ID")

        binding.mementoButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(
                    ProduktBesschreibungFragmentDirections
                        .actionProduktBesschreibungFragmentToMementoFragment(getId!!)
                )
        }

        mainViewModel.medicineList.observe(viewLifecycleOwner) {
            for (i in it) {
                if (i.id == getId) {
                    binding.ProdukuktBeschreibungDescription.text = i.description
                    binding.ProduktBeschreibungImage.load(i.image)
                    binding.produktBeschreibungTitle.text = i.name

                    mainViewModel.dailymedicine.observe(viewLifecycleOwner) { daily ->

                        when (i in daily) {
                            true -> {
                                binding.buttonAddButton.text = "In Hausapotheke entfernen"
                                binding.buttonAddButton.setOnClickListener {
                                    mainViewModel.removeFromDailyMedicine(i)
                                }
                            }
                            false -> {
                                binding.buttonAddButton.text = "In Hausapotheke hinzufügen"
                                binding.buttonAddButton.setOnClickListener {
                                    mainViewModel.addTooDailyMedicineList(i)
                                }
                            }
                        }
                    }

                    break
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
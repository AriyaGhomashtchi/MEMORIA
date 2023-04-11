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
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.databinding.FragmentMementoBinding

class MementoFragment : Fragment() {

    private var _binding: FragmentMementoBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel : MainViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMementoBinding.inflate(inflater, container, false)
        val view = binding.root
        val getID = requireArguments().getString("mementoID")

        binding.mementoMementoButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mementoFragment_to_notizenFragment)
        }

        mainViewModel.medicineList.observe(viewLifecycleOwner){
            for (i in it) {
                if (i.id == getID){
                    binding.memoriaEmpfiehltText.text = i.name
                    binding.mementoImage.load(i.image)
                    binding.mementoMementoButton.setOnClickListener {
                        Navigation.findNavController(view)
                            .navigate(MementoFragmentDirections.actionMementoFragmentToNotizenFragment(i.id))
                    }
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

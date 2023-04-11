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
import com.ghomashtchi.memoria.databinding.FragmentConfirmBinding

class ConfirmFragment : Fragment() {

    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel : MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var getID = requireArguments().getString("confirmID")

         _binding = FragmentConfirmBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.confirmButton.text = "Uhrzeit ${mainViewModel.time.value} \n" +
                "${mainViewModel.date.value}"

        mainViewModel.medicineList.observe(viewLifecycleOwner){
            for (i in it){
                if(i.id==getID){
                    binding.imageConfirmImage.load(i.image)
                }
            }
        }
        binding.confirmButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_confirmFragment_to_hausapothekefragment)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

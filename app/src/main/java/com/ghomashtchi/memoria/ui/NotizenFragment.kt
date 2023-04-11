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
import com.ghomashtchi.memoria.databinding.FragmentNotizenBinding


class NotizenFragment : Fragment() {

    private var _binding: FragmentNotizenBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotizenBinding.inflate(inflater,container,false)
        val view = binding.root

        val getID = requireArguments().getString("notizenID")

        mainViewModel.medicineList.observe(viewLifecycleOwner){
            for (i in it){
                if (i.id == getID) {
                    binding.medicineText.text = i.name
                    binding.medicineImage.load(i.image)

                    binding.mementoButtontwo.setOnClickListener {
                        var time = binding.editTextTime.text.toString()
                        var date = binding.editTextDate.text.toString()

                        if (!time.isNullOrEmpty()&&!date.isNullOrEmpty()){
                            mainViewModel.mementotime(time)
                            mainViewModel.mementodate(date)

                            Navigation.findNavController(view)
                                .navigate(NotizenFragmentDirections.actionNotizenFragmentToConfirmFragment(getID!!))
                        }
                    }
                }
            }
        }

        binding.mementoButtontwo.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_notizenFragment_to_confirmFragment)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
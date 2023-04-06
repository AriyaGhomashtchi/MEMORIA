package com.ghomashtchi.memoria.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.databinding.FragmentMementoBinding

class MementoFragment : Fragment() {

    private var _binding: FragmentMementoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMementoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.mementoButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mementoFragment_to_notizenFragment)
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

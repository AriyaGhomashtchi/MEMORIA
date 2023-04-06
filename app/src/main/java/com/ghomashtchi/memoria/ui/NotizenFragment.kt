package com.ghomashtchi.memoria.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.databinding.FragmentNotizenBinding


class NotizenFragment : Fragment() {

    private var _binding: FragmentNotizenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotizenBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.mementoButton2.setOnClickListener {
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
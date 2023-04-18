package com.ghomashtchi.memoria.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.Repository
import com.ghomashtchi.memoria.data.model.UserAccount
import com.ghomashtchi.memoria.data.remote.MedicineApi
import com.ghomashtchi.memoria.databinding.FragmentRegistrationButtonBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistrationButtonBinding.inflate(inflater, container, false)
        val view = binding.root

        val account = Repository(MedicineApi).loadAccounts()

        binding.loginButton.setOnClickListener {
            val username = binding.nameEditButton.text.toString()
            val password1 = binding.passwordEditButton.text.toString()
            val password2 = binding.passwordRepititionEditButton.text.toString()

            if (password1 == password2) {
                account.add(UserAccount(username.lowercase(), password2))
                Navigation.findNavController(view)
                    .navigate(R.id.action_registrationButton_to_hausapothekefragment)
            } else {
                Toast.makeText(requireContext(), "Passwörter stimmt nicht ein", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
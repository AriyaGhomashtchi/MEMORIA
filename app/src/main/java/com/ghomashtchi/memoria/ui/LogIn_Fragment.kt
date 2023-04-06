package com.ghomashtchi.memoria.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ghomashtchi.memoria.R
import com.ghomashtchi.memoria.data.Repository
import com.ghomashtchi.memoria.data.model.UserAccount
import com.ghomashtchi.memoria.data.remote.MedicineApi
import com.ghomashtchi.memoria.databinding.FragmentLogInBinding

class LogIn_Fragment : Fragment() {

    private var _binding : FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        val accounts = Repository(MedicineApi).loadAccounts()

        binding.loginLoginButton.setOnClickListener {
            val username = binding.logInNameEdit.text.toString()
            val password = binding.logInPasswordEdit.text.toString()
            var accountExist = true

            for (i in accounts) {
                if (i == UserAccount(username.lowercase(), password)) {
                    accountExist = true
                    Navigation.findNavController(view)
                        .navigate(R.id.action_logIn_Fragment_to_hausapothekefragment)
                    break
                } else {
                    accountExist = false
                }
            }

            if (!accountExist) {
                Toast.makeText(requireContext(), "Benuztername oder Passwort ist falsch", Toast.LENGTH_LONG).show()
            }
        }

        binding.registerButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_logIn_Fragment_to_registrationButton)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
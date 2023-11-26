package com.fiveg.montenegreen.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.databinding.FragmentLoginBinding
import com.fiveg.montenegreen.util.GlobalData

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[AuthViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.loginButton.setOnClickListener {
            binding.loginProgress.visibility = View.VISIBLE

            binding.loginEtEmail.error = null
            binding.loginEtPassword.error = null

            val email = binding.loginEtEmail.editText!!.text.toString()
            val password = binding.loginEtPassword.editText!!.text.toString()
            viewModel.login(email, password)
        }

        viewModel.token.observeForever {
            binding.loginProgress.visibility = View.GONE

            GlobalData.saveToken(requireContext(), it)
            findNavController().navigate(R.id.action_login_to_zadaci)
        }

        viewModel.errors.observeForever {
            binding.loginProgress.visibility = View.GONE

            if (it.has("email")) {
                binding.loginEtEmail.error = it["email"].asJsonArray[0].asString
            }

            if (it.has("password")) {
                binding.loginEtPassword.error = it["password"].asJsonArray[0].asString
            }
        }

        viewModel.message.observeForever {
            binding.loginProgress.visibility = View.GONE

            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        binding.loginTextRegisterInstead.setOnClickListener {
            binding.loginProgress.visibility = View.GONE

            findNavController().navigate(R.id.action_login_to_register)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
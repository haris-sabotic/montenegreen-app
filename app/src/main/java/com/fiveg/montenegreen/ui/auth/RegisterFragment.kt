package com.fiveg.montenegreen.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.databinding.FragmentRegisterBinding
import com.fiveg.montenegreen.util.GlobalData

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

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

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.registerButton.setOnClickListener {
            binding.registerProgress.visibility = View.VISIBLE

            binding.registerEtName.error = null
            binding.registerEtEmail.error = null
            binding.registerEtPassword.error = null
            binding.registerEtConfirmPassword.error = null

            val name = binding.registerEtName.editText!!.text.toString()
            val email = binding.registerEtEmail.editText!!.text.toString()
            val password = binding.registerEtPassword.editText!!.text.toString()
            val confirmPassword = binding.registerEtConfirmPassword.editText!!.text.toString()

            viewModel.register(name, email, password, confirmPassword)
        }

        viewModel.token.observeForever {
            binding.registerProgress.visibility = View.GONE

            GlobalData.saveToken(requireContext(), it)
            findNavController().navigate(R.id.action_register_to_mapa)
        }

        viewModel.errors.observeForever {
            binding.registerProgress.visibility = View.GONE

            if (it.has("name")) {
                binding.registerEtName.error = it["name"].asJsonArray[0].asString
            }

            if (it.has("email")) {
                binding.registerEtEmail.error = it["email"].asJsonArray[0].asString
            }

            if (it.has("password")) {
                binding.registerEtPassword.error = it["password"].asJsonArray[0].asString
            }

            if (it.has("confirm_password")) {
                binding.registerEtConfirmPassword.error = it["confirm_password"].asJsonArray[0].asString
            }
        }

        viewModel.message.observeForever {
            binding.registerProgress.visibility = View.GONE

            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        binding.registerTextLoginInstead.setOnClickListener {
            binding.registerProgress.visibility = View.GONE

            findNavController().navigate(R.id.action_register_to_login)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
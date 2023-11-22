package com.fiveg.montenegreen.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.databinding.FragmentProfilBinding
import com.fiveg.montenegreen.util.GlobalData

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[ProfilViewModel::class.java]

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.profilButtonLogout.setOnClickListener {
            GlobalData.saveToken(requireContext(), null)
            findNavController().navigate(R.id.action_profil_to_login)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
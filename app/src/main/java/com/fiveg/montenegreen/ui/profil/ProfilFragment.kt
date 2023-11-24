package com.fiveg.montenegreen.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.databinding.FragmentProfilBinding
import com.fiveg.montenegreen.ui.zadaci.ZadaciRecyclerViewAdapter
import com.fiveg.montenegreen.util.GlobalData

class ProfilFragment : Fragment() {
    private val viewModel: ProfilViewModel by activityViewModels()

    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profilRecyclerZadaci.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (viewModel.user.value == null) {
            viewModel.loadUserData(GlobalData.getToken()!!)
        }
        if (viewModel.zadaci.value == null) {
            viewModel.loadUserCompletedZadaci(GlobalData.getToken()!!)
        }

        viewModel.user.observe(viewLifecycleOwner) {
            binding.profilTextName.text = it.name
            binding.profilTextEmail.text = it.email
            binding.profilTextPoints.text = it.points.toString()
        }

        viewModel.zadaci.observe(viewLifecycleOwner) {
            binding.profilRecyclerZadaci.adapter = ZadaciRecyclerViewAdapter(requireContext(), it)
        }

        binding.profilButtonLogout.setOnClickListener {
            GlobalData.saveToken(requireContext(), null)
            findNavController().navigate(R.id.action_profil_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
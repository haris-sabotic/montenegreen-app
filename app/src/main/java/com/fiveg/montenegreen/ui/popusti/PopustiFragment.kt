package com.fiveg.montenegreen.ui.popusti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.databinding.FragmentPopustiBinding
import com.fiveg.montenegreen.models.PopustModel
import com.fiveg.montenegreen.models.UserModel
import com.fiveg.montenegreen.ui.profil.ProfilViewModel
import com.fiveg.montenegreen.ui.zadaci.ZadaciRecyclerViewAdapter
import com.fiveg.montenegreen.util.GlobalData
import okhttp3.internal.notifyAll

class PopustiFragment : Fragment() {
    private val viewModel: PopustiViewModel by activityViewModels()
    private val profilViewModel: ProfilViewModel by activityViewModels()

    private var _binding: FragmentPopustiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopustiBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popustiRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (viewModel.popusti.value == null) {
            viewModel.loadPopusti()
        }

        viewModel.popusti.observe(viewLifecycleOwner) {
            binding.popustiRecycler.adapter = PopustiRecyclerViewAdapter(requireContext(), it, 0)
            profilViewModel.loadUserData(GlobalData.getToken()!!)
        }

        profilViewModel.user.observe(viewLifecycleOwner) { user ->
            binding.popustiRecycler.adapter?.let {
                (it as PopustiRecyclerViewAdapter).updateUserPoints(user.points)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
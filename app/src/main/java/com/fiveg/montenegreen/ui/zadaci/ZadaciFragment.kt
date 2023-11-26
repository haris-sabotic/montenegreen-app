package com.fiveg.montenegreen.ui.zadaci

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.databinding.FragmentZadaciBinding
import com.fiveg.montenegreen.ui.popusti.PopustiFragmentDirections

class ZadaciFragment : Fragment() {
    private val viewModel: ZadaciViewModel by activityViewModels()

    private var _binding: FragmentZadaciBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZadaciBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.zadaciRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (viewModel.zadaci.value == null) {
            viewModel.loadZadaci()
        }

        viewModel.zadaci.observe(viewLifecycleOwner) {
            binding.zadaciRecycler.adapter = ZadaciRecyclerViewAdapter(requireContext(), it) { zadatakModel ->
                val action = ZadaciFragmentDirections.actionZadaciToWholeZadatak(zadatakModel)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.fiveg.montenegreen.ui.zadaci

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.databinding.FragmentZadaciBinding

class ZadaciFragment : Fragment() {

    private var _binding: FragmentZadaciBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[ZadaciViewModel::class.java]

        _binding = FragmentZadaciBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.zadaciRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.loadZadaci()

        viewModel.zadaci.observeForever {
            binding.zadaciRecycler.adapter = ZadaciRecyclerViewAdapter(requireContext(), it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
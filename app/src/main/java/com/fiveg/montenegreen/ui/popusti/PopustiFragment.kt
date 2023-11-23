package com.fiveg.montenegreen.ui.popusti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.databinding.FragmentPopustiBinding
import com.fiveg.montenegreen.ui.zadaci.ZadaciRecyclerViewAdapter

class PopustiFragment : Fragment() {

    private var _binding: FragmentPopustiBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[PopustiViewModel::class.java]

        _binding = FragmentPopustiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.popustiRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.loadPopusti()

        viewModel.popusti.observeForever {
            binding.popustiRecycler.adapter = PopustiRecyclerViewAdapter(requireContext(), it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
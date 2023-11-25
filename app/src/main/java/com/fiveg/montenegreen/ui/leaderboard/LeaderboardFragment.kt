package com.fiveg.montenegreen.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.databinding.FragmentLeaderboardBinding

class LeaderboardFragment : Fragment() {
    private val viewModel: LeaderboardViewModel by activityViewModels()

    private var _binding: FragmentLeaderboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.leaderboardRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (viewModel.leaderboard.value == null) {
            viewModel.loadLeaderboard()
        }

        viewModel.leaderboard.observe(viewLifecycleOwner) {
            binding.leaderboardRecycler.adapter = LeaderboardRecyclerViewAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
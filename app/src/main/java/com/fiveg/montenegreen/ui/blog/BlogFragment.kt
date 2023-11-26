package com.fiveg.montenegreen.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiveg.montenegreen.databinding.FragmentBlogBinding

class BlogFragment : Fragment() {
    private val viewModel: BlogViewModel by activityViewModels()

    private var _binding: FragmentBlogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.blogRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (viewModel.blog.value == null) {
            viewModel.loadBlog()
        }

        viewModel.blog.observe(viewLifecycleOwner) {
            binding.blogRecycler.adapter = BlogRecyclerViewAdapter(requireContext(), it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
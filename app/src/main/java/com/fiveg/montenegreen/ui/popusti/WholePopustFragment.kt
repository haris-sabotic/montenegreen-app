package com.fiveg.montenegreen.ui.popusti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fiveg.montenegreen.databinding.FragmentWholePopustBinding
import com.fiveg.montenegreen.util.GlobalData
import kotlin.math.roundToInt

class WholePopustFragment : Fragment() {
    private val args: WholePopustFragmentArgs by navArgs()

    private var _binding: FragmentWholePopustBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWholePopustBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popust = args.popustModel
        val userPoints = args.userPoints

        binding.wholePopustTextName.text = popust.name
        binding.wholePopustTextLocation.text = popust.location
        binding.wholePopustTextDescription.text = popust.description

        binding.wholePopustTextFractionLeft.text = userPoints.toString()
        binding.wholePopustTextFractionRight.text = popust.points.toString()

        val percentage = 100F * (userPoints.toFloat() / popust.points.toFloat())
        val progress = minOf(100F, percentage)
        binding.wholePopustProgress.progress = progress.roundToInt()

        val fractionLayout = binding.wholePopustTextFractionLeft.parent as LinearLayout
        fractionLayout.setOnClickListener {
            Toast.makeText(context, "${progress}%", Toast.LENGTH_SHORT).show()
        }

        Glide.with(requireContext())
            .load(GlobalData.PHOTO_URL_PREFIX + popust.photoUrl)
            .into(binding.wholePopustImage);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
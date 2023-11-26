package com.fiveg.montenegreen.ui.zadaci

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fiveg.montenegreen.databinding.FragmentWholeZadatakBinding
import com.fiveg.montenegreen.util.GlobalData

class WholeZadatakFragment : Fragment() {
    private val args: WholeZadatakFragmentArgs by navArgs()

    private var _binding: FragmentWholeZadatakBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWholeZadatakBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val zadatak = args.zadatakModel

        binding.wholeZadatakTextName.text = zadatak.name
        binding.wholeZadatakTextLocation.text = zadatak.location
        binding.wholeZadatakTextPoints.text = zadatak.points.toString()
        binding.wholeZadatakTextDescription.text = zadatak.description

        Glide.with(requireContext())
            .load(GlobalData.PHOTO_URL_PREFIX + zadatak.photoUrl)
            .into(binding.wholeZadatakImage);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
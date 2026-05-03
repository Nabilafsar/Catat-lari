package com.upn.catatlari.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import com.upn.catatlari.databinding.FragmentAddRunBinding
import com.upn.catatlari.model.RunEntity
import com.upn.catatlari.viewmodel.RunViewModel

class AddRunFragment : Fragment() {

    private lateinit var binding: FragmentAddRunBinding
    val runViewModel: RunViewModel by activityViewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAddRunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val existingRun = arguments?.getParcelable<RunEntity>("runEntity")

        if (existingRun != null) {
            binding.etDate.setText(existingRun.runDate)
            binding.etRunDistance.setText(existingRun.runDistance.toString())
            binding.etRunDuration.setText(existingRun.runDuration.toString())
            binding.btnSaveRun.text = "Update"
        }

        binding.btnSaveRun.setOnClickListener {
            val runDate = binding.etDate.text.toString().trim()
            val runDurationStr = binding.etRunDuration.text.toString().trim()
            val runDistanceStr = binding.etRunDistance.text.toString().trim()

            if (runDate.isEmpty() || runDurationStr.isEmpty() || runDistanceStr.isEmpty()) {
                Toast.makeText(requireContext(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val runDuration = runDurationStr.toIntOrNull()
            val runDistance = runDistanceStr.toIntOrNull()

            if (runDuration == null || runDistance == null) {
                Toast.makeText(requireContext(), "Jarak dan durasi harus berupa angka!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (existingRun != null) {
                runViewModel.updateRun(RunEntity(existingRun.id, runDate, runDistance, runDuration))
            } else {
                runViewModel.addRun(RunEntity(runDate = runDate, runDistance = runDistance, runDuration = runDuration))
            }

            findNavController().popBackStack()
        }
    }
}

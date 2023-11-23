package com.example.setbro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.setbro.databinding.FragmentMuscGroupBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MuscleGroups : Fragment() {

    private var _binding: FragmentMuscGroupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMuscGroupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chestPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.latsPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.bicepsPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.tricepsPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.deltsPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.quadsPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.absPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
        binding.calvesPanel.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
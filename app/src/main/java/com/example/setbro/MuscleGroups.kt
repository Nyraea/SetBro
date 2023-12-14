package com.example.setbro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.setbro.databinding.FragmentMuscGroupBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MuscleGroups : Fragment() {

    private var _binding: FragmentMuscGroupBinding? = null
    private var chosenDay: Int? = null
    private var chosenMonth: Int? = null
    private var chosenYear: Int? = null
    private var muscleID: Int? = null

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
            muscleID = 0
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.latsPanel.setOnClickListener {
            muscleID = 1
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.bicepsPanel.setOnClickListener {
            muscleID = 2
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.tricepsPanel.setOnClickListener {
            muscleID = 3
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.deltsPanel.setOnClickListener {
            muscleID = 4
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.quadsPanel.setOnClickListener {
            muscleID = 5
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.absPanel.setOnClickListener {
            muscleID = 6
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.calvesPanel.setOnClickListener {
            muscleID = 7
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)
            bundle.putInt("muscleID", muscleID!!)
            findNavController().navigate(R.id.action_MuscGroup_to_LogValues, bundle)
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_MuscGroup_to_Home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        Log.e("String", arguments?.getInt("day").toString())
        chosenDay = arguments?.getInt("day")
        chosenMonth = arguments?.getInt("month")
        chosenYear = arguments?.getInt("year")
    }

}
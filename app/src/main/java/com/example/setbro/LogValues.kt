package com.example.setbro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.fragment.findNavController
import com.example.setbro.databinding.FragmentLogValuesBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class LogValues : Fragment() {

    private var _binding: FragmentLogValuesBinding? = null
    private var chosenDay: Int? = null
    private var chosenMonth: Int? = null
    private var chosenYear: Int? = null
    private var muscleID: Int? = null
    private var weightValue: Int? = null
    private var date: String? = null
    private lateinit var snackbar : Snackbar
    private lateinit var database: DatabaseReference
    private lateinit var  customLayout : View

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customLayout = inflater.inflate(R.layout.successful_entry_toast, container, false)
        database = Firebase.database.reference
        _binding = FragmentLogValuesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_SHORT)

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.black))
        snackbarLayout.addView(customLayout, 0)

        binding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                weightValue = progress
                binding.progressText.text = "$weightValue" + "lbs"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something when the user starts tracking touch on the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something when the user stops tracking touch on the SeekBar
            }
        })

        binding.saveBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)

            if (muscleID == 0) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 1) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 2) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 3) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 4) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 5) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 6) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else if (muscleID == 7) {
                writeNewEntry(date.toString(),weightValue.toString())
                snackbar.show()
            }
            else {
                Toast.makeText(getContext(), "MuscleID is null", Toast.LENGTH_SHORT ).show()
            }


            findNavController().navigate(R.id.action_LogValues_to_MuscGroup, bundle)

        }

        binding.backBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("day", chosenDay!!)
            bundle.putInt("month", chosenMonth!!)
            bundle.putInt("year", chosenYear!!)

            findNavController().navigate(R.id.action_LogValues_to_MuscGroup, bundle)
        }

    }

    override fun onResume() {
        super.onResume()
        chosenDay = arguments?.getInt("day")
        chosenMonth = arguments?.getInt("month")
        chosenYear = arguments?.getInt("year")
        muscleID = arguments?.getInt("muscleID")
        date = chosenYear.toString() +"-"+ chosenMonth.toString() +"-"+ chosenDay.toString()
        binding.dateText.text = "$date"


    }

    fun writeNewEntry(entryID: String, weight: String) {
        database.child("entries").child(muscleID.toString()).child(entryID).setValue(weight)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.setbro

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.setbro.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var postReference: DatabaseReference
    private val postList = HashMap<String, String>()
    private var childNode = 0
    private lateinit var lineGraphView: GraphView // Declare as a property
    private lateinit var snackbar : Snackbar
    private lateinit var customLayout : View
    private lateinit var textView : TextView


    data class Post (

        val entryID: String? = null,
        val value: Int = 0
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customLayout = inflater.inflate(R.layout.pr_value_toast, container, false)
        postReference = Firebase.database.getReference("entries")
        postReference.addValueEventListener(postListener)
        database = Firebase.database.reference
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_SHORT)
        lineGraphView = binding.graph

        // set graph viewport bounds
        lineGraphView.getViewport().setYAxisBoundsManual(true);
        lineGraphView.getViewport().setMinY(0.00);
        lineGraphView.getViewport().setMaxY(300.00);

        // enable scaling and scrolling
        lineGraphView.getViewport().setScalable(true);
        lineGraphView.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        // activate horizontal scrolling
        lineGraphView.getViewport().setScrollable(true);
        lineGraphView.getViewport().setScrollableY(true); // activate vertical scrolling
        lineGraphView.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(requireActivity())
        lineGraphView.gridLabelRenderer.numHorizontalLabels = 2 // only 4 because of the space
// legend

        lineGraphView.getLegendRenderer().setVisible(true);
        lineGraphView.getLegendRenderer().setFixedPosition(0, 0);
        lineGraphView.gridLabelRenderer.setHumanRounding(false, true)

        binding.DatePicker1.setOnDateChangedListener {view, year, monthOfYear, dayOfMonth ->
            var chosenYear = year
            var chosenMonth = monthOfYear + 1
            var chosenDay = dayOfMonth

            val bundle = Bundle()
            bundle.putInt("day", chosenDay) // Replace "key" and "value" with your variable names and values
            bundle.putInt("month", chosenMonth)
            bundle.putInt("year", chosenYear)

            if (chosenDay != null && chosenMonth != null && chosenYear != null) {
                findNavController().navigate(R.id.action_Home_to_MuscGroup, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }
    val allDataPoints = mutableListOf<DataPoint>()
    val postListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            // Get the values from the snapshot
            for (postSnapshot in dataSnapshot.children) {
                postList.clear()
                // Access each subchild under the entry
                for (childSnapshot in postSnapshot.children) {
                    var key = childSnapshot.key // Key of the subchild
                    var value = childSnapshot.value // Value of the subchild

                    if (key != null && value != null) {
                        // Assuming value is a map or holds the necessary data
                        var dateString = key // Assuming the date is the key
                        var valueString = value.toString() // Assuming the data is a string

                        dateString?.let { postList[it] = value.toString() }
                        key = null
                        value = null

                    }
                }
                for ((key, value) in postList) {
                    Log.i("hashMapData", "Key: $key, Value: $value")
                }

                var dataPoints = mutableListOf<DataPoint>()
                // Assuming postList is a Map<String, Int> containing date strings as keys and Int as values
                for ((dateString, value) in postList) {
                    // Convert the dateString to a Date object
                    var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    var date: Date = dateFormat.parse(dateString) ?: continue // Skip if parsing fails
                    // Create a DataPoint for each date and its corresponding value
                    Log.i("parsedMapData", "Key: $date, Value: $value")
                    if (value != "null") {
                        // Only parse if the value is not "null"

                        var dataPoint = DataPoint(date, value.toDouble())

                        dataPoints.add(dataPoint)
                    }
                    else
                    {
                        var doubleValue = value.toDoubleOrNull() ?: 0.0 // Use 0.0 as default if parsing fails
                        var dataPoint = DataPoint(date, doubleValue)

                        dataPoints.add(dataPoint)
                    }

                }
                for (dataPoint in dataPoints) {
                    Log.d("dataPointsData", "Date: ${dataPoint.x}, Value: ${dataPoint.y}")
                }
                var sortedDataPoints = dataPoints.sortedBy { it.x }

                var sortedDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

                // Log the sorted dataPoints
                sortedDataPoints.forEach { dataPoint ->
                    var formattedDate = sortedDateFormat.format(dataPoint.x)
                    Log.d("FormattedDataPointsData", "Date: ${formattedDate}, Value: ${dataPoint.y}")
                }
                Log.d("FormattedDataPointsData", "Child Node: $childNode")
                var sortedDataArray = sortedDataPoints.toTypedArray()
                allDataPoints.addAll(sortedDataPoints)
                // Log the contents of the array
                sortedDataArray.forEachIndexed { index, dataPoint ->
                    Log.i("sortedDataArray", "Index: $index, DataPoint: $dataPoint")
                }


                var series = LineGraphSeries(sortedDataArray)

                // Set the color for the series (use Color.BLACK).
                if (childNode == 0) {
                    series.color = Color.BLACK
                    series.setTitle("Chest");
                    Log.i("LUPAYPAY", "BLACK")
                } else if (childNode == 1) {
                    series.color = Color.RED
                    series.setTitle("Lats");
                    Log.i("LUPAYPAY", "RED")
                } else if (childNode == 2) {
                    series.color = Color.BLUE
                    series.setTitle("Biceps");
                    Log.i("LUPAYPAY", "BLUE")
                } else if (childNode == 3) {
                    series.color = Color.GREEN
                    series.setTitle("Triceps");
                    Log.i("LUPAYPAY", "GREEN")
                } else if (childNode == 4) {
                    series.color = Color.YELLOW
                    series.setTitle("Delts");
                    Log.i("LUPAYPAY", "YELLOW")
                } else if (childNode == 5) {
                    series.color = Color.WHITE
                    series.setTitle("Quads");
                    Log.i("LUPAYPAY", "WHITE")
                } else if (childNode == 6) {
                    series.color = Color.MAGENTA
                    series.setTitle("Abs");
                    Log.i("LUPAYPAY", "MAGENTA")
                } else if (childNode == 7) {
                    series.color = Color.CYAN
                    series.setTitle("Calves");
                    Log.i("LUPAYPAY", "CYAN")
                } else{}

                series.setDrawDataPoints(true);
                series.setDataPointsRadius(10f);
                series.setThickness(8);
                series.setOnDataPointTapListener { series, dataPoint ->
                    val snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_SHORT)
                    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
                    snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.black))

                    val customLayout = layoutInflater.inflate(R.layout.pr_value_toast, null)
                    val textView = customLayout.findViewById<TextView>(R.id.pr_text)
                    var sortedDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    var simpleDate = dateFormat.format(dataPoint.x)
                    textView.text = simpleDate.toString() + " | PR Weight: " + dataPoint.y.toString() + "lbs"

                    snackbarLayout.addView(customLayout, 0)
                    snackbar.show()
                }

                // Add data series to the graph view.
                    lineGraphView.addSeries(series)

                Log.i("BETSILOG", "SERIES: $series")
                childNode++
            }
            val oldestDate = allDataPoints.minByOrNull { it.x }?.x
            val mostRecentDate = allDataPoints.maxByOrNull { it.x }?.x
            oldestDate?.let { lineGraphView.getViewport().setMinX(it) }
            mostRecentDate?.let { lineGraphView.getViewport().setMaxX(it) }
            lineGraphView.getViewport().setXAxisBoundsManual(true)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    }
package com.gilmazin.sensors.fragments

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.gilmazin.sensors.R
import com.gilmazin.sensors.databinding.FragmentProximidadeBinding

class  ProximidadeFragment : Fragment(), SensorEventListener {
  lateinit var binding: FragmentProximidadeBinding

  private lateinit var sensorManager: SensorManager
  private var proximity: Sensor? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proximidade, container, false)

    sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    return binding.root

  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

  }

  override fun onSensorChanged(event: SensorEvent?) {
    // 1
    if (event == null) {
      Toast.makeText(requireContext(),"proximity null", Toast.LENGTH_LONG).show();
    }
    // 2
    if (event!!.values[0] < proximity!!.maximumRange) {
      requireActivity().window.decorView.setBackgroundColor(Color.YELLOW)
    } else {
      requireActivity().window.decorView.setBackgroundColor(Color.GREEN)
    }
  }

  override fun onResume() {
    // Register a listener for the sensor.
    super.onResume()

      sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
  }

  override fun onPause() {
    // Be sure to unregister the sensor when the activity pauses.
    super.onPause()
    sensorManager.unregisterListener(this)
  }
}
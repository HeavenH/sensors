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
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.gilmazin.sensors.R
import com.gilmazin.sensors.databinding.FragmentLuzBinding


class LuzFragment : Fragment(), SensorEventListener {
  private lateinit var sensorManager: SensorManager
  private lateinit var brightness: Sensor
  lateinit var binding: FragmentLuzBinding
  var maxValue: Float = 0.0f

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_luz, container, false)

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    maxValue = brightness.maximumRange

    // Inflate the layout for this fragment
    return binding.root
  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
  }

  override fun onSensorChanged(event: SensorEvent?) {
    if (event != null) {
      var value = event!!.values[0]
      var newValue = (255f * value / maxValue)
      binding.lightText.setText("VALUE" + newValue)
      if (event == null) {
        Toast.makeText(requireContext(), "light null", Toast.LENGTH_LONG).show();
      }
    }
  }

  override fun onResume() {
    super.onResume()
    sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
  }

  override fun onPause() {
    // Be sure to unregister the sensor when the activity pauses.
    super.onPause()
    sensorManager.unregisterListener(this)
  }
}
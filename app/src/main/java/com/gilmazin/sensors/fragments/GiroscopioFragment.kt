package com.gilmazin.sensors.fragments

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import com.gilmazin.sensors.R
import com.gilmazin.sensors.databinding.FragmentGiroscopioBinding


class GiroscopioFragment : Fragment(), SensorEventListener {
  lateinit var binding: FragmentGiroscopioBinding

  private lateinit var sensorManager: SensorManager
  private  var giroscopio: Sensor? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_giroscopio, container, false)

    sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    // Inflate the layout for this fragment
    return binding.root
  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
  }


  override fun onSensorChanged(event: SensorEvent?) {

    if (event != null) {
      binding.axtext.setText("giroscopio" + event.values[0].toFloat().toString() )
    }
  }
  override fun onResume() {
    // Register a listener for the sensor.
    super.onResume()

    sensorManager.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_NORMAL)
  }

  override fun onPause() {
    // Be sure to unregister the sensor when the activity pauses.
    super.onPause()
    sensorManager.unregisterListener(this)
  }
}
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
import com.gilmazin.sensors.databinding.FragmentAcelerometroBinding


class AcelerometroFragment : Fragment(), SensorEventListener {
  lateinit var binding: FragmentAcelerometroBinding

  private lateinit var sensorManager: SensorManager
  lateinit var accelerometer: Sensor

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_acelerometro, container, false)

    sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    // Inflate the layout for this fragment
    return binding.root
  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

  }


  override fun onSensorChanged(event: SensorEvent?) {
    if (event != null) {
      binding.xtextA.setText("X" + event.values[0].toFloat().toString() )
      binding.ytextA.setText("Y" + event.values[1].toFloat().toString() )
      binding.ztext.setText("Z" + event.values[2].toFloat().toString() )

    }
  }

  override fun onResume() {
    // Register a listener for the sensor.
    super.onResume()

    sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
  }

  override fun onPause() {
    // Be sure to unregister the sensor when the activity pauses.
    super.onPause()
    sensorManager.unregisterListener(this)
  }
}
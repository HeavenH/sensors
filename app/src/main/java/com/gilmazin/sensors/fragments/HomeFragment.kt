package com.gilmazin.sensors.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.gilmazin.sensors.R
import com.gilmazin.sensors.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
  lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

    binding.apply {
      proximityButton.setOnClickListener{
        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_proximidadeFragment)
      }
      accelerometerButton.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_acelerometroFragment)
      }
      lightButton.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_luzFragment)
      }
      giroscopioButton.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_giroscopioFragment)
      }
    }
    // Inflate the layout for this fragment
    return binding.root
  }
}
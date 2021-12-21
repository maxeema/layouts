package com.example.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.layouts.databinding.MainFragmentBinding
import com.example.layouts.nearby.NearbyFragment
import com.example.layouts.diseases.DiseasesFragment

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.diseases.setOnClickListener {
            parentFragmentManager.commit {
                add<DiseasesFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
        binding.hospital.setOnClickListener {
            parentFragmentManager.commit {
                add<NearbyFragment>(R.id.fragment_container_view, args = Bundle().apply {
                    putString("id", "hospital")
                })
                addToBackStack(null)
            }
        }
        binding.labs.setOnClickListener {
            parentFragmentManager.commit {
                add<NearbyFragment>(R.id.fragment_container_view, args = Bundle().apply {
                    putString("id", "labs")
                })
                addToBackStack(null)
            }
        }
        binding.doctor.setOnClickListener {
            parentFragmentManager.commit {
                add<NearbyFragment>(R.id.fragment_container_view, args = Bundle().apply {
                    putString("id", "doctor")
                })
                addToBackStack(null)
            }
        }
        return binding.root
    }
}
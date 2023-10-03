package com.abdoulaye.jcdecaux.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abdoulaye.jcdecaux.R
import com.abdoulaye.jcdecaux.databinding.FragmentStationDetailBinding
import com.abdoulaye.jcdecaux.model.BicycleStation

class BicycleStationDetailFragment: Fragment() {
    private var station: BicycleStation? = null
    private lateinit var dataBinding: FragmentStationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_station_detail,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            station = BicycleStationDetailFragmentArgs.fromBundle(
                it
            ).bicycleStation
        }
        dataBinding.bicycleStation = station
    }
}
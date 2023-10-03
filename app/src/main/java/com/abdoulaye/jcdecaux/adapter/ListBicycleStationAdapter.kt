package com.abdoulaye.jcdecaux.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abdoulaye.jcdecaux.R
import com.abdoulaye.jcdecaux.databinding.ItemBikeStationBinding
import com.abdoulaye.jcdecaux.fragment.ListBicycleStationsFragmentDirections
import com.abdoulaye.jcdecaux.model.BicycleStation
import com.abdoulaye.jcdecaux.listener.StationClickListener

class ListBicycleStationAdapter(private val listStation: ArrayList<BicycleStation>) :
    RecyclerView.Adapter<ListBicycleStationAdapter.StationsViewHolder>(), StationClickListener {

    fun updatelistStation(newlistStation: List<BicycleStation>) {
        listStation.clear()
        listStation.addAll(newlistStation)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBikeStationBinding>(inflater, R.layout.item_bike_station, parent, false)

        return StationsViewHolder(binding)
    }

    override fun getItemCount() = listStation.size

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        holder.view.bicycleStation = listStation[position]
        holder.view.listener = this
    }

    override fun onClick(v: View) {
        getDetails(v)
    }

    private fun getDetails(v: View) {
        for (bicycleStation in listStation) {
            if (v.tag == bicycleStation.name) {
                val action = ListBicycleStationsFragmentDirections.actionDetail(bicycleStation)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }

    class StationsViewHolder(var view: ItemBikeStationBinding): RecyclerView.ViewHolder(view.root)



}
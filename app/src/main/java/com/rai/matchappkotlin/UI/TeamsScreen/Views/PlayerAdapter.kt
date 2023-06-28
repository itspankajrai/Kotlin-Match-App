package com.rai.matchappkotlin.UI.TeamsScreen.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.Player
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.databinding.PlayerViewItemBinding


class PlayerAdapter(private val data: ArrayList<Player>)  : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlayerViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(private val binding: PlayerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Player) {
            var _role="";

            if(item.is_captain==true) _role=" (C)"
            if(item.is_keeper==true) _role=" (WK)"
            binding.textPlayerName.text = item.name_full+_role
            binding.textPlayerRole.text = item.position
            binding.textPlayerTeam.text=item.team_name

            val bundle= Bundle()
            val gson= Gson()
            bundle.putString("player_info",gson.toJson(item))
            binding.root.setOnClickListener {
               it.findNavController().navigate(R.id.player_info_dialog, bundle)

            }
        }
    }
}
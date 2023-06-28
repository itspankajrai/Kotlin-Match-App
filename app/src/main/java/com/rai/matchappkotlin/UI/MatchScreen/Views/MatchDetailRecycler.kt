package com.rai.matchappkotlin.UI.MatchScreen.Views

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.databinding.MatchDetailItemBinding

class MatchDetailRecycler(private val data: ArrayList<MatchDetail>)  : RecyclerView.Adapter<MatchDetailRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MatchDetailItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(private val binding: MatchDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MatchDetail) {
            binding.textTeamHome.text = item.teams[item.match.teamHome]?.name_full ?: "null"
            binding.textTeamAway.text = item.teams[item.match.teamAway]?.name_full ?: "null"
            binding.textVenue.text=item.match.venue.name
            binding.textDate.text=item.match.match.date
            binding.textSeriesDetails.text=item.match.series.name
            binding.textReferee.text=item.match.officials.referee
            binding.textUmpires.text=item.match.officials.umpires
            binding.textMatchStatus.text=item.match.status
            binding.textMatchResult.text=item.match.result
            binding.navigateButtonDetails.setOnClickListener(View.OnClickListener {
                it.findNavController().navigate(R.id.team_screen)
            })
        }
    }
}
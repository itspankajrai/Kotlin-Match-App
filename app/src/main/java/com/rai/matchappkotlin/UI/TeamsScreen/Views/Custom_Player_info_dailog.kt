package com.rai.matchappkotlin.UI.TeamsScreen.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.DataModels.Player
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.databinding.PlayerInfoDialogBinding

class Custom_Player_info_dailog : DialogFragment() {
    private var _binding: PlayerInfoDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = PlayerInfoDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        val args=arguments
        val data= args?.getString("player_info")
        val gson = Gson()
        val player_info = gson.fromJson(data, Player::class.java)


        binding.textBattingStyle.text=player_info.batting.style
        binding.textBattingRuns.text=player_info.batting.runs
        binding.textBattingStrikeRate.text=player_info.batting.strikeRate
        binding.textBattingAverage.text=player_info.batting.average

        binding.textBowlingStyle.text=player_info.bowling.style
        binding.textBowlingWickets.text=player_info.bowling.wickets
        binding.textBowlingAverage.text=player_info.bowling.average
        binding.textBowlingEconomyRate.text=player_info.bowling.economy_rate

        return view
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}

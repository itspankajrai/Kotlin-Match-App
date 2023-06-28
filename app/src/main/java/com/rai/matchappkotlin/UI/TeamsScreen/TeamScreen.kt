package com.rai.matchappkotlin.UI.TeamsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.DataModels.Player
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.UI.MatchScreen.MatchScreen
import com.rai.matchappkotlin.UI.MatchScreen.Views.MatchDetailRecycler
import com.rai.matchappkotlin.UI.TeamsScreen.Views.PlayerAdapter
import com.rai.matchappkotlin.databinding.FragmentMatchScreenBinding
import com.rai.matchappkotlin.databinding.FragmentTeamScreenBinding

class TeamScreen : Fragment() {

    companion object {
        fun newInstance() = TeamScreen()
    }

    private var _binding: FragmentTeamScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TeamScreenViewModel




    var dataList=ArrayList<Player>()
    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var adapter: PlayerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[TeamScreenViewModel::class.java]
        val args=arguments
        val data= args?.getString("data")
        val gson = Gson()
        val matchDetail = gson.fromJson(data, MatchDetail::class.java)
        adapter= PlayerAdapter(dataList)
        teamRecyclerView=binding.teamRecyclerView
        teamRecyclerView.adapter = adapter
        teamRecyclerView.layoutManager = LinearLayoutManager(context)



        Log.d("TAG", "decoded: "+matchDetail.toString())
        teams_selection(matchDetail,"home")
        teams_selection(matchDetail,"away")
        return view


    }

    fun teams_selection(matchDetail: MatchDetail,selection: String){
        if(selection == "home"){
            matchDetail.teams[matchDetail.match.teamHome]?.players?.forEach { (s, player) ->
                dataList.add(player)
            }
            adapter.notifyDataSetChanged()
        }
        if(selection == "away"){
            matchDetail.teams[matchDetail.match.teamAway]?.players?.forEach { (s, player) ->
                dataList.add(player)
            }
            adapter.notifyDataSetChanged()
        }

    }


}
package com.rai.matchappkotlin.UI.TeamsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
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

    private lateinit var mode_Spinner: Spinner
    var mode_selection= arrayOf("Home Team","Away Team","Both")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[TeamScreenViewModel::class.java]


        //parsing args
        val args=arguments
        val data= args?.getString("data")
        val gson = Gson()
        val matchDetail = gson.fromJson(data, MatchDetail::class.java)


        //recyclerView
        adapter= PlayerAdapter(dataList)
        teamRecyclerView=binding.teamRecyclerView
        mode_Spinner=binding.mySpinner
        teamRecyclerView.adapter = adapter
        teamRecyclerView.layoutManager = LinearLayoutManager(context)

        //spinner
        val selection_adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, mode_selection) }
        selection_adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mode_Spinner.adapter=selection_adapter


        //selection logic
        mode_Spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                teams_selection(matchDetail,mode_selection[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        return view


    }

    fun teams_selection(matchDetail: MatchDetail,selection: String){
        if(dataList.size>0){
            dataList.clear()
        }
        if(selection == "Home Team" || selection=="Both"){
            matchDetail.teams[matchDetail.match.teamHome]?.players?.forEach { (s, player) ->
                player.team_name=matchDetail.teams[matchDetail.match.teamHome]?.name_full ?: "null"
                dataList.add(player)
            }
            adapter.notifyDataSetChanged()
        }
        if(selection == "Away Team"|| selection=="Both"){
            matchDetail.teams[matchDetail.match.teamAway]?.players?.forEach { (s, player) ->
                player.team_name=matchDetail.teams[matchDetail.match.teamAway]?.name_full ?: "null"
                dataList.add(player)
            }
            adapter.notifyDataSetChanged()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}
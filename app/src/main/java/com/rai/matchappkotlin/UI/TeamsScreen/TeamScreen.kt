package com.rai.matchappkotlin.UI.TeamsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.UI.MatchScreen.MatchScreen
import com.rai.matchappkotlin.databinding.FragmentMatchScreenBinding
import com.rai.matchappkotlin.databinding.FragmentTeamScreenBinding

class TeamScreen : Fragment() {

    companion object {
        fun newInstance() = TeamScreen()
    }

    private var _binding: FragmentTeamScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TeamScreenViewModel

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

        Log.d("TAG", "decoded: "+matchDetail.toString())

        return view


    }


}